package edu.hbaha.spring.controllers.client;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.hbaha.spring.domain.Tb_Product;
import edu.hbaha.spring.models.CartItem;
import edu.hbaha.spring.models.CategoryDto;
import edu.hbaha.spring.service.CategoryService;
import edu.hbaha.spring.service.ProductService;
import edu.hbaha.spring.service.WishListService;

@Controller
@RequestMapping("wishList")
public class WishListClientController {
	@Autowired
	ProductService productService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	WishListService wishListService;
	
	@Autowired
	CategoryService categoryService;
	
	@ModelAttribute("categoriesLv1")
	public List<CategoryDto> getCategories() {
		return categoryService.findByParrentID(null).stream().map(item ->{
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}
	
	public List<CategoryDto> getCategoriesByParrentID(Integer parrentID){
		return categoryService.findByParrentID(parrentID).stream().map(item ->{
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}
	
	@ModelAttribute("categoriesLv2")
	public Map<Integer, List<CategoryDto>> getMap (){
		Map<Integer, List<CategoryDto>> map = new HashMap<>();
		for(CategoryDto x: getCategoriesByParrentID(null)) {
			map.put(x.getCateID(), getCategoriesByParrentID(x.getCateID()));
		}
			
		return map;
	}
	
	@GetMapping("")
	public String getShoppingCart(HttpSession session) {
		if(wishListService.getCount() == 0) {
			session.setAttribute("num", wishListService.getCount());
			return "client/wishlist/wishlistEmpty";
		}
		Collection<CartItem> cartItems = wishListService.getCartItem();
		session.setAttribute("wishlist", cartItems);
		session.setAttribute("num", wishListService.getCount());
		return "client/wishlist/list";
	}
	
	@GetMapping("add/{productID}")
	public String add(@PathVariable("productID") Integer productID) {
		if(session.getAttribute("username") == null) {
			return "redirect:/login";
		}
		Optional<Tb_Product> opt= productService.findById(productID);
		Tb_Product product = opt.get();
		if(product != null) {
			CartItem item = new CartItem();
			BeanUtils.copyProperties(product, item);
			wishListService.add(item); 
		}
		return "redirect:/wishList";
	}
	
	@GetMapping("remove/{productID}")
	public String remove(@PathVariable("productID") Integer productID) {
		wishListService.remove(productID);
		return "redirect:/wishList";
	}
	
	@GetMapping("clear")
	public String clear() {
		wishListService.clear();
		return "redirect:/wishList";
	}
}
