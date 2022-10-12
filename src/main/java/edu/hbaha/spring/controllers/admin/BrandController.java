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

import edu.hbaha.spring.domain.Tb_Brand;
import edu.hbaha.spring.models.BrandDto;
import edu.hbaha.spring.service.BrandService;

@Controller
@RequestMapping("admin/brands")
public class BrandController {
	@Autowired
	BrandService brandService;

	@GetMapping("addOrEdit")
	public String add(Model model) {
		BrandDto dto = new BrandDto();
		dto.setIsEdit(false);
		model.addAttribute("brand", dto);
		return "admin/brands/addOrEdit";
	}
	@RequestMapping("view/{id}")
	public String add(Model model, @PathVariable("id") int id) {
		Optional<Tb_Brand> otp = brandService.findById(id);
		Tb_Brand list = otp.get();
		model.addAttribute("view", list);
		return "admin/brands/view";
	}

	@GetMapping("edit/{id}")
	public ModelAndView edit(ModelMap model, @PathVariable("id") int id) {
		Optional<Tb_Brand> otp = brandService.findById(id);
		BrandDto dto = new BrandDto();
		if (otp.isPresent()) {
			Tb_Brand entity = otp.get();
			BeanUtils.copyProperties(entity, dto);
			model.addAttribute("brand", dto);
			dto.setIsEdit(true);
			return new ModelAndView("admin/brands/addOrEdit", model);
		}
		model.addAttribute("message", "Thương hiệu không tồn tại");
		return new ModelAndView("forward:/admin/brands", model);
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("brand") BrandDto dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("/admin/brands/addOrEdit");
		}
		Tb_Brand entity = new Tb_Brand();
		BeanUtils.copyProperties(dto, entity);
		brandService.save(entity);
		if (!dto.getIsEdit()) {
			model.addAttribute("message", "Thêm mới thương hiệu thành công");
		} else {
			model.addAttribute("message", "Cập nhập thương hiệu thành công");
		}

		return new ModelAndView("forward:/admin/brands/view/"+entity.getId() , model);
	}

	@GetMapping("delete/{id}")
	public ModelAndView delete(Model model, @PathVariable("id") Integer id) {
		if (brandService.findById(id).isPresent()) {
			brandService.deleteById(id);
			model.addAttribute("message", "Thương hiệu đã được xóa");
			return new ModelAndView("forward:/admin/brands");
		}
		model.addAttribute("message", "Không tồn tại thương hiệu này");
		return new ModelAndView("forward:/admin/brands");
	}

//	@RequestMapping("")
//	public String list(ModelMap model) {
//		List<Tb_Productbrand> list = brandService.findAll();
//		model.addAttribute("brands", list);
//		return "admin/brands/list";
//	}
//
//	@GetMapping("search")
//	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
//		List<Tb_Productbrand> list = null;
//		if (StringUtils.hasText(name)) {
//			list = brandService.findByNameContaining(name);
//		} else {
//			list = brandService.findAll();
//		}
//
//		model.addAttribute("brands", list);
//
//		return "admin/brands/search";
//	}

	@GetMapping("")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
		Page<Tb_Brand> resultPage = null;

		if (StringUtils.hasText(name)) {
			resultPage = brandService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = brandService.findAll(pageable);
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

		model.addAttribute("brandPage", resultPage);

		return "admin/brands/searchPaginated";
	}
	
}
