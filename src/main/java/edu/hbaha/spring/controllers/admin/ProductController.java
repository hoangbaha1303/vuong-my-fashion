package edu.hbaha.spring.controllers.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.hbaha.spring.domain.Tb_Product;
import edu.hbaha.spring.domain.Tb_ProductCategory;
import edu.hbaha.spring.models.BrandDto;
import edu.hbaha.spring.models.CategoryDto;
import edu.hbaha.spring.models.ProductDto;
import edu.hbaha.spring.models.SupplierDto;
import edu.hbaha.spring.service.BrandService;
import edu.hbaha.spring.service.CategoryService;
import edu.hbaha.spring.service.ProductService;
import edu.hbaha.spring.service.StorageService;
import edu.hbaha.spring.service.SupplierService;

@Controller
@RequestMapping("admin/products")
public class ProductController {
	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@Autowired
	BrandService brandService;

	@Autowired
	SupplierService supplierService;

	@Autowired
	StorageService storageService;

	@ModelAttribute("categories")
	public List<CategoryDto> getCategories() {
		return categoryService.findAll().stream().map(item -> {
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
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

	@GetMapping("add")
	public String add(Model model) {
		ProductDto dto = new ProductDto();
		dto.setIsEdit(false);
		model.addAttribute("product", dto);
		return "admin/products/addOrEdit";
	}

	@RequestMapping("view/{cateID}")
	public String add(Model model, @PathVariable("cateID") int cateID) {
		Optional<Tb_Product> otp = productService.findById(cateID);
		Tb_Product list = otp.get();
		model.addAttribute("view", list);
		return "admin/products/view";
	}
	
	@GetMapping("edit/{productID}")
	public ModelAndView edit(ModelMap model, @PathVariable("productID") int productID) {
		Optional<Tb_Product> otp = productService.findById(productID);
		ProductDto dto = new ProductDto();
		if (otp.isPresent()) {
			Tb_Product entity = otp.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);
			model.addAttribute("product", dto);
			return new ModelAndView("admin/products/addOrEdit", model);
		}
		model.addAttribute("message", "Mã sản phẩm không tồn tại");
		return new ModelAndView("forward:/admin/products", model);
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("product") ProductDto dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("/admin/products/addOrEdit");
		}
		Tb_Product entity = null;
		if (!dto.getIsEdit()) {
			entity = new Tb_Product();
			BeanUtils.copyProperties(dto, entity);
			entity.setCreateDate(new Date());

			if (!dto.getImageFile().isEmpty()) {
				UUID uuid = UUID.randomUUID();
				String uuString = uuid.toString();

				entity.setImage(storageService.getStoredFilename(dto.getImageFile(), uuString));
				storageService.store(dto.getImageFile(), entity.getImage());
			}
			productService.save(entity);
			model.addAttribute("message", "Thêm mới sản phẩm thành công");
		} else {
			Optional<Tb_Product> otp = productService.findById(dto.getProductID());
			entity = otp.get();
			dto.setImage(entity.getImage());
			BeanUtils.copyProperties(dto, entity);
			entity.setCreateDate(entity.getCreateDate());
			entity.setUpdateDate(new Date());

			if (!dto.getImageFile().isEmpty()) {
				UUID uuid = UUID.randomUUID();
				String uuString = uuid.toString();

				entity.setImage(storageService.getStoredFilename(dto.getImageFile(), uuString));
				storageService.store(dto.getImageFile(), entity.getImage());
			}
			productService.save(entity);
			model.addAttribute("message", "Cập nhập sản phẩm thành công");
		}

		return new ModelAndView("forward:/admin/products/view/"+entity.getProductID(), model);
	}

	@GetMapping("images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("delete/{productID}")
	public ModelAndView delete(Model model, @PathVariable("productID") Integer productID) throws IOException {
		Optional<Tb_Product> opt = productService.findById(productID);
		if (opt.isPresent()) {
			if (!StringUtils.isEmpty(opt.get().getImage())) {
				storageService.delete(opt.get().getImage());
			}
			productService.delete(opt.get());
			model.addAttribute("message", "Sản phẩm đã được xóa");
		} else {
			model.addAttribute("message", "Không tồn tại sản phẩm này");
		}
		return new ModelAndView("forward:/admin/products");
	}

//	@RequestMapping("")
//	public String list(ModelMap model) {
//		List<Tb_Product> list = productService.findAll();
//		model.addAttribute("products", list);
//		return "admin/products/list";
//	}
//
//	@GetMapping("search")
//	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
//		List<Tb_ProductCategory> list = null;
//		if (StringUtils.hasText(name)) {
//			list = categoryService.findByNameContaining(name);
//		} else {
//			list = categoryService.findAll();
//		}
//
//		model.addAttribute("products", list);
//
//		return "admin/products/search";
//	}

	@GetMapping("")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
		Page<Tb_Product> resultPage = null;

		if (StringUtils.hasText(name)) {
			resultPage = productService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = productService.findAll(pageable);
		}

		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if (totalPages > 5) {
				if (end == totalPages)
					start = end - 5;
				else if (start == 1)
					end = start + 5;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("productPage", resultPage);

		return "admin/products/searchPaginated";
	}
}
