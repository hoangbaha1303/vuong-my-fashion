package edu.hbaha.spring.controllers.client;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import edu.hbaha.spring.domain.Tb_Order;
import edu.hbaha.spring.domain.Tb_OrderDetail;
import edu.hbaha.spring.domain.Tb_OrderDetailPK;
import edu.hbaha.spring.domain.Tb_Product;
import edu.hbaha.spring.domain.Tb_User;
import edu.hbaha.spring.models.CartItem;
import edu.hbaha.spring.models.CategoryDto;
import edu.hbaha.spring.service.CategoryService;
import edu.hbaha.spring.service.ContactService;
import edu.hbaha.spring.service.EmailService;
import edu.hbaha.spring.service.OrderDetailService;
import edu.hbaha.spring.service.OrderService;
import edu.hbaha.spring.service.ProductService;
import edu.hbaha.spring.service.ShoppingCartService;
import edu.hbaha.spring.service.UserService;

@Controller
@RequestMapping("carts")
public class ShoppingCartClientController {
	@Autowired
	ProductService productService;

	@Autowired
	EmailService emailService;
	
	@Autowired
	OrderService orderService;

	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	UserService userService;

	@Autowired
	ShoppingCartService shoppingCartService;

	@Autowired
	HttpSession session;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ContactService contactService;
	
	@ModelAttribute("categoriesLv1")
	public List<CategoryDto> getCategories() {
		return categoryService.findByParrentID(null).stream().map(item -> {
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}

	public List<CategoryDto> getCategoriesByParrentID(Integer parrentID) {
		return categoryService.findByParrentID(parrentID).stream().map(item -> {
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}

	@ModelAttribute("categoriesLv2")
	public Map<Integer, List<CategoryDto>> getMap() {
		Map<Integer, List<CategoryDto>> map = new HashMap<>();
		for (CategoryDto x : getCategoriesByParrentID(null)) {
			map.put(x.getCateID(), getCategoriesByParrentID(x.getCateID()));
		}

		return map;
	}

	@RequestMapping("")
	public String getShoppingCart(HttpSession session) {
		if (shoppingCartService.getCount() == 0) {
			session.setAttribute("total", shoppingCartService.getAmount());
			session.setAttribute("num", shoppingCartService.getCount());
			return "client/cart/cartEmpty";
		}
		Collection<CartItem> cartItems = shoppingCartService.getCartItem();
		session.setAttribute("cartItems", cartItems);
		session.setAttribute("total", shoppingCartService.getAmount());
		session.setAttribute("num", shoppingCartService.getCount());
		return "client/cart/list";
	}

	@GetMapping("add/{productID}")
	public String add(@PathVariable("productID") Integer productID) {
		if (session.getAttribute("username") == null) {
			return "redirect:/login";
		}
		Optional<Tb_Product> opt = productService.findById(productID);
		Tb_Product product = opt.get();
		if (product != null) {
			CartItem item = new CartItem();
			BeanUtils.copyProperties(product, item);
			item.setQuantity(1);
			shoppingCartService.add(item);
		}
		return "redirect:/carts";
	}

	@GetMapping("remove/{productID}")
	public String remove(@PathVariable("productID") Integer productID) {
		shoppingCartService.remove(productID);
		return "redirect:/carts/";
	}

	@PostMapping("update")
	public String update(@RequestParam("productID") Integer productID, @RequestParam("quantity") Integer quantity) {
		shoppingCartService.update(productID, quantity);
		return "redirect:/carts";
	}

	@GetMapping("clear")
	public String clear() {
		shoppingCartService.clear();
		return "redirect:/carts";
	}

	@GetMapping("checkout")
	public String getCheckOut(ModelMap model) {
		if (session.getAttribute("username") == null) {
			return "redirect:/login";
		}
		if(shoppingCartService.getCartItem().isEmpty()) {
			return  "redirect:/carts";
		}
		Optional<Tb_User> account = userService.findByUserName(session.getAttribute("username").toString());
		model.addAttribute("account", account.get());
		return "client/cart/checkout";
	}

	@PostMapping("checkout")
	public ModelAndView postCheckout(ModelMap model) {
		if(session.getAttribute("username") == null) {
			return new ModelAndView("redirect:/login");
		}
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		Optional<Tb_User> account = userService.findByUserName(session.getAttribute("username").toString());
		Tb_Order order = new Tb_Order(null,account.get().getId(),null,account.get().getAddress(),new Date(),1);
		Integer id = orderService.save(order).getOrderID();
		String body ="Mã đơn hàng: "+id+"\n";
		for(CartItem x : shoppingCartService.getCartItem()) {
			Tb_OrderDetail orderDetail = new Tb_OrderDetail(new Tb_OrderDetailPK(id, x.getProductID()),x.getPromotionPrice() != null?x.getPromotionPrice(): x.getPrice(), x.getName(), x.getQuantity());
			orderDetailService.save(orderDetail);
			body += "SP: "+x.getName()+" - Giá: "+ formatter.format(x.getPromotionPrice() != null?x.getPromotionPrice(): x.getPrice())+" VNĐ - Số lượng: "+x.getQuantity()+".\n";
		}
		body += "Tổng thanh toán là: "+formatter.format(shoppingCartService.getAmount())+ " VNĐ";
		emailService.sendMail(account.get().getEmail(), "Đặt Hàng Thành Công",body);
		emailService.sendMail(contactService.findById(1).get().getEmail(),"Bạn Có Đơn Đặt Hàng Từ Khách Hàng" , body);
		shoppingCartService.clear();
		model.addAttribute("message", "Đặt hàng thành công");
		return new ModelAndView("forward:/carts", model);
	}

}
