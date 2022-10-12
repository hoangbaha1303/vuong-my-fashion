package edu.hbaha.spring.controllers.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.hbaha.spring.domain.Tb_PostComment;
import edu.hbaha.spring.domain.Tb_Product;
import edu.hbaha.spring.domain.Tb_ProductComment;
import edu.hbaha.spring.domain.Tb_User;
import edu.hbaha.spring.models.BrandDto;
import edu.hbaha.spring.models.CategoryDto;
import edu.hbaha.spring.models.CommentGetDto;
import edu.hbaha.spring.models.ProductCommentDto;
import edu.hbaha.spring.models.SupplierDto;
import edu.hbaha.spring.service.BrandService;
import edu.hbaha.spring.service.CategoryService;
import edu.hbaha.spring.service.CommentService;
import edu.hbaha.spring.service.ProductService;
import edu.hbaha.spring.service.StorageService;
import edu.hbaha.spring.service.SupplierService;
import edu.hbaha.spring.service.UserService;

@Controller
@RequestMapping("products")
public class ProductClientController {

	@Autowired
	ProductService productService;

	@Autowired
	BrandService brandService;

	@Autowired
	SupplierService supplierService;

	@Autowired
	CommentService commentService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CategoryService categoryService;
	
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

	@ModelAttribute("brands")
	public List<BrandDto> getBrands() {
		return brandService.findAll().stream().map(item -> {
			BrandDto dto = new BrandDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}

	@ModelAttribute("suppliers")
	public List<SupplierDto> getSuppliers() {
		return supplierService.findAll().stream().map(item -> {
			SupplierDto dto = new SupplierDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}
	
	@GetMapping("detail/{productID}")
	public String getDetail(ModelMap model, @PathVariable("productID") int productID) {
		// Product Detail
		Optional<Tb_Product> product = productService.findById(productID);
		model.addAttribute("product", product.get());
		
		//Comment Product
		List<Tb_ProductComment> listComment = commentService.findByProductID(productID);
		model.addAttribute("countComment", commentService.countComment(productID));
		model.addAttribute("avgVoteComment", commentService.avgCommentVote(productID));
		model.addAttribute("comments", listComment);
		
		//Related Product
		List<Tb_Product> relatedProduct = productService.findByCateID(product.get().getCateID());		
		model.addAttribute("relatedProduct", relatedProduct);
		
		//Star Related Product
		List<CommentGetDto> listStar = new ArrayList<>();
		for(Tb_Product x : relatedProduct) {
			listStar.add(new CommentGetDto(x.getProductID(), commentService.avgCommentVote(x.getProductID()) ,commentService.countComment(x.getProductID())));
		}
		model.addAttribute("starRelated", listStar);
		
		return "client/products/detail";
	}
	
	@PostMapping("comment")
	public String postComment(ModelMap model, HttpSession session,
			@RequestParam("vote") byte vote, @RequestParam("detail") String detail, @RequestParam("productID") Integer productID) {
		if(session.getAttribute("username") == null) {
			return "redirect:/login";
		}
		Tb_ProductComment comment = new Tb_ProductComment();
		Optional<Tb_User> user = userService.findByUserName(session.getAttribute("username").toString());
		comment.setName(user.get().getName());
		comment.setDetail(detail);
		comment.setEmail(user.get().getEmail());
		comment.setProductID(productID);
		comment.setCreateBy(user.get().getId());
		comment.setCreateDate(new Date());
		comment.setVote(vote);
		commentService.save(comment);
		model.addAttribute("message", "Bình luận thành công");
		return "redirect:/products/detail/"+productID;
	}
	
//	@GetMapping("category/{cateID}")
//	public String getCategory(ModelMap model, @PathVariable("cateID") int cateID) {
//		List<Tb_Product> list = productService.findByCateID(cateID);
//		model.addAttribute("products", list);
//		return "client/products/list";
//	}
	
	@GetMapping({"search","category/{cateID}"})
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, @RequestParam("sort")  Optional<Integer> sort,@PathVariable(name="cateID", required=false) Integer cateID) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(8);
		int pageSort= sort.orElse(1);
		Pageable pageable = null;
		switch(pageSort) {
			case 1: 
				pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
				break;
			case 2: 
				pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name").descending());
				break;
			case 3: 
				pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("createDate").descending());
				break;
			case 4: 
				pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("createDate"));
				break;
			case 5: 
				pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("price"));
				break;
			case 6: 
				pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("price").descending());
				break;
			case 7: 
				pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("viewCount"));
				break;
			case 8: 
				pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("viewCount").descending());
				break;
		}
		
		model.addAttribute("sort", pageSort);
		Page<Tb_Product> resultPage = null;

		if (StringUtils.hasText(name)) {
			resultPage = productService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = productService.findAll(pageable);
		}
		
		if(cateID != null) {
			resultPage =  new PageImpl<>(productService.findByCateID(cateID)) ;
		}
		
		if(resultPage.isEmpty()) {
			return "client/products/emptySearch";
		}
		
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if (totalPages > 8) {
				if (end == totalPages)
					start = end - 8;
				else if (start == 1)
					end = start + 8;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		//Lấy sao cho sản phẩm
		
		List<CommentGetDto> listStar = new ArrayList<>();
		for(Tb_Product x : resultPage) {
			listStar.add(new CommentGetDto(x.getProductID(), commentService.avgCommentVote(x.getProductID()) ,commentService.countComment(x.getProductID())));
		}
		
		model.addAttribute("listStar", listStar);
		model.addAttribute("productPage", resultPage);
		model.addAttribute("countSearch", resultPage.stream().count());
		return "client/products/list";
	}
}
