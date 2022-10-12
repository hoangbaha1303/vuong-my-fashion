package edu.hbaha.spring.controllers.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hbaha.spring.domain.Tb_Product;
import edu.hbaha.spring.models.CategoryDto;
import edu.hbaha.spring.models.CommentGetDto;
import edu.hbaha.spring.service.CategoryService;
import edu.hbaha.spring.service.CommentService;
import edu.hbaha.spring.service.FeedBackService;
import edu.hbaha.spring.service.ProductService;
import edu.hbaha.spring.service.ShoppingCartService;
import edu.hbaha.spring.service.StorageService;

@Controller
@RequestMapping("")
public class HomeClientController {
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	FeedBackService feedBackService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	StorageService storageService;
	
	@GetMapping("images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@ModelAttribute("categories")
	public List<CategoryDto> getCategories() {
		return categoryService.findAll().stream().map(item ->{
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}
	
	@ModelAttribute("categoriesLv1")
	public List<CategoryDto> getCategorieslv1() {
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
	
	@ModelAttribute("newArrivals")
	public List<Tb_Product> getNewArrivals(){
		return productService.findTop7ByOrderByCreateDateDesc();
	}
	
	@ModelAttribute("trending")
	public List<Tb_Product> getTrending(){
		return productService.findTop8ByOrderByViewCountDesc();
	}
	
	@ModelAttribute("hot")
	public List<Tb_Product> getHot(){
		return productService.findTop4ByHotAfterOrderByHotAsc(new Date());
	}
	
	@RequestMapping
	public String index(ModelMap model) {
		List<CommentGetDto> starNewArrivals = new ArrayList<>();
		for(Tb_Product x : getNewArrivals()) {
			starNewArrivals.add(new CommentGetDto(x.getProductID(), commentService.avgCommentVote(x.getProductID()) ,commentService.countComment(x.getProductID())));
		}
		
		List<CommentGetDto> starTrending = new ArrayList<>();
		for(Tb_Product x : getTrending()) {
			starTrending.add(new CommentGetDto(x.getProductID(), commentService.avgCommentVote(x.getProductID()) ,commentService.countComment(x.getProductID())));
		}
		
		List<CommentGetDto> starHot = new ArrayList<>();
		for(Tb_Product x : getHot()) {
			starHot.add(new CommentGetDto(x.getProductID(), commentService.avgCommentVote(x.getProductID()) ,commentService.countComment(x.getProductID())));
		}
		
		model.addAttribute("starNewArrivals", starNewArrivals);
		model.addAttribute("starTrending", starTrending);
		model.addAttribute("starHot", starHot);
		model.addAttribute("feedbacks", feedBackService.findByStatus(true));
		session.setAttribute("total", shoppingCartService.getAmount());
		session.setAttribute("num", shoppingCartService.getCount());
		return "client/home/index";
	}
	
}
