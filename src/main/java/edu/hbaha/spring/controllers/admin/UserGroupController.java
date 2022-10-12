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

import edu.hbaha.spring.domain.Tb_UserGroup;
import edu.hbaha.spring.models.UserGroupDto;
import edu.hbaha.spring.service.UserGroupService;

@Controller
@RequestMapping("admin/userGroups")
public class UserGroupController {
	@Autowired
	UserGroupService userGroupService;
		
	@GetMapping("addOrEdit")
	public String add(Model model) {
		UserGroupDto dto = new UserGroupDto();
		dto.setIsEdit(false);
		model.addAttribute("userGroup", dto);
		return "admin/userGroups/addOrEdit";
	}
	@RequestMapping("view/{id}")
	public String add(Model model, @PathVariable("id") int id) {
		Optional<Tb_UserGroup> otp = userGroupService.findById(id);
		Tb_UserGroup list = otp.get();
		model.addAttribute("view", list);
		return "admin/userGroups/view";
	}

	@GetMapping("edit/{id}")
	public ModelAndView edit(ModelMap model, @PathVariable("id") int id) {
		Optional<Tb_UserGroup> otp = userGroupService.findById(id);
		UserGroupDto dto = new UserGroupDto();
		if (otp.isPresent()) {
			Tb_UserGroup entity = otp.get();
			BeanUtils.copyProperties(entity, dto);
			model.addAttribute("userGroup", dto);
			dto.setIsEdit(true);
			return new ModelAndView("admin/userGroups/addOrEdit", model);
		}
		model.addAttribute("message", "Nhóm người dùng không tồn tại");
		return new ModelAndView("forward:/admin/userGroups", model);
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("userGroup") UserGroupDto dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("/admin/userGroups/addOrEdit");
		}
		Tb_UserGroup entity = new Tb_UserGroup();
		BeanUtils.copyProperties(dto, entity);
		userGroupService.save(entity);
		if (!dto.getIsEdit()) {
			model.addAttribute("message", "Thêm mới nhóm người dùng thành công");
		} else {
			model.addAttribute("message", "Cập nhập nhóm người dùng thành công");
		}

		return new ModelAndView("forward:/admin/userGroups/view/"+entity.getId() , model);
	}

	@GetMapping("delete/{id}")
	public ModelAndView delete(Model model, @PathVariable("id") Integer id) {
		if (userGroupService.findById(id).isPresent()) {
			userGroupService.deleteById(id);
			model.addAttribute("message", "Nhóm người dùng đã được xóa");
			return new ModelAndView("forward:/admin/userGroups");
		}
		model.addAttribute("message", "Không tồn tại nhóm người dùng này");
		return new ModelAndView("forward:/admin/userGroups");
	}

//	@RequestMapping("")
//	public String list(ModelMap model) {
//		List<Tb_ProductuserGroup> list = userGroupService.findAll();
//		model.addAttribute("userGroups", list);
//		return "admin/userGroups/list";
//	}
//
//	@GetMapping("search")
//	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
//		List<Tb_ProductuserGroup> list = null;
//		if (StringUtils.hasText(name)) {
//			list = userGroupService.findByNameContaining(name);
//		} else {
//			list = userGroupService.findAll();
//		}
//
//		model.addAttribute("userGroups", list);
//
//		return "admin/userGroups/search";
//	}

	@GetMapping("")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
		Page<Tb_UserGroup> resultPage = null;

		if (StringUtils.hasText(name)) {
			resultPage = userGroupService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = userGroupService.findAll(pageable);
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

		model.addAttribute("userGroupPage", resultPage);

		return "admin/userGroups/searchPaginated";
	}
	
}
