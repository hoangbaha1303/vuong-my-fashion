package edu.hbaha.spring.controllers.admin;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.servlet.ModelAndView;

import edu.hbaha.spring.domain.Tb_Supplier;
import edu.hbaha.spring.models.SupplierDto;
import edu.hbaha.spring.service.SupplierService;

@Controller
@RequestMapping("admin/suppliers")
public class SupplierController {
	@Autowired
	SupplierService supplierService;

	@GetMapping("addOrEdit")
	public String add(Model model) {
		SupplierDto dto = new SupplierDto();
		dto.setIsEdit(false);
		model.addAttribute("supplier", dto);
		return "admin/suppliers/addOrEdit";
	}
	@RequestMapping("view/{id}")
	public String add(Model model, @PathVariable("id") int id) {
		Optional<Tb_Supplier> otp = supplierService.findById(id);
		Tb_Supplier list = otp.get();
		model.addAttribute("view", list);
		return "admin/suppliers/view";
	}

	@GetMapping("edit/{id}")
	public ModelAndView edit(ModelMap model, @PathVariable("id") int id) {
		Optional<Tb_Supplier> otp = supplierService.findById(id);
		SupplierDto dto = new SupplierDto();
		if (otp.isPresent()) {
			Tb_Supplier entity = otp.get();
			BeanUtils.copyProperties(entity, dto);
			model.addAttribute("supplier", dto);
			dto.setIsEdit(true);
			return new ModelAndView("admin/suppliers/addOredit", model);
		}
		model.addAttribute("message", "Mã loại sản phẩm không tồn tại");
		return new ModelAndView("forward:/admin/suppliers", model);
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("supplier") SupplierDto dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("/admin/suppliers/addOrEdit");
		}
		Tb_Supplier entity = new Tb_Supplier();
		BeanUtils.copyProperties(dto, entity);
		supplierService.save(entity);
		if (!dto.getIsEdit()) {
			model.addAttribute("message", "Thêm mới nhà phân phối thành công");
		} else {
			model.addAttribute("message", "Cập nhập nhà phân phối thành công");
		}

		return new ModelAndView("forward:/admin/suppliers/view/"+entity.getId() , model);
	}

	@GetMapping("delete/{id}")
	public ModelAndView delete(Model model, @PathVariable("id") Integer id) {
		if (supplierService.findById(id).isPresent()) {
			supplierService.deleteById(id);
			model.addAttribute("message", "Loại sản phẩm đã được xóa");
			return new ModelAndView("forward:/admin/suppliers");
		}
		model.addAttribute("message", "Không tồn tại loại sản phẩm này");
		return new ModelAndView("forward:/admin/suppliers");
	}

//	@RequestMapping("")
//	public String list(ModelMap model) {
//		List<Tb_Supplier> list = supplierService.findAll();
//		model.addAttribute("suppliers", list);
//		return "admin/suppliers/list";
//	}
//
//	@GetMapping("search")
//	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
//		List<Tb_Supplier> list = null;
//		if (StringUtils.hasText(name)) {
//			list = supplierService.findByNameContaining(name);
//		} else {
//			list = supplierService.findAll();
//		}
//
//		model.addAttribute("suppliers", list);
//
//		return "admin/suppliers/search";
//	}

	@GetMapping("")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
		Page<Tb_Supplier> resultPage = null;

		if (StringUtils.hasText(name)) {
			resultPage = supplierService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = supplierService.findAll(pageable);
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

		model.addAttribute("supplierPage", resultPage);

		return "admin/suppliers/searchPaginated";
	}
	
}
