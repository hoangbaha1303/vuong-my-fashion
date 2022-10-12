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

import edu.hbaha.spring.domain.Tb_User;
import edu.hbaha.spring.models.UserDto;
import edu.hbaha.spring.models.UserGroupDto;
import edu.hbaha.spring.service.UserGroupService;
import edu.hbaha.spring.service.UserService;

@Controller
@RequestMapping("admin/users")
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	UserGroupService userGroupService;

	@ModelAttribute("userGroups")
	public List<UserGroupDto> getuserGroups() {
		return userGroupService.findAll().stream().map(item -> {
			UserGroupDto dto = new UserGroupDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}

	@GetMapping("addOrEdit")
	public String add(Model model) {
		UserDto dto = new UserDto();
		dto.setIsEdit(false);
		model.addAttribute("user", dto);
		return "admin/users/addOrEdit";
	}

	@RequestMapping("view/{id}")
	public String add(Model model, @PathVariable("id") int id) {
		Optional<Tb_User> otp = userService.findById(id);
		Tb_User list = otp.get();
		model.addAttribute("view", list);
		return "admin/users/view";
	}

	@GetMapping("edit/{id}")
	public ModelAndView edit(ModelMap model, @PathVariable("id") int id) {
		Optional<Tb_User> otp = userService.findById(id);
		UserDto dto = new UserDto();
		if (otp.isPresent()) {
			Tb_User entity = otp.get();
			BeanUtils.copyProperties(entity, dto);
			model.addAttribute("user", dto);
			dto.setIsEdit(true);
			return new ModelAndView("admin/users/addOredit", model);
		}
		model.addAttribute("message", "Tài khoản không tồn tại");
		return new ModelAndView("forward:/admin/users", model);
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("user") UserDto dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("/admin/users/addOrEdit");
		}
		Tb_User entity = null;
		if (!dto.getIsEdit()) {
			Optional<Tb_User> opt = userService.findByUserName(dto.getUserName());
			if (opt.isPresent()) {
				entity = new Tb_User();
				BeanUtils.copyProperties(dto, entity);
				entity.setCreateDate(new Date());
				userService.save(entity);
				model.addAttribute("message", "Thêm mới tài khoản thành công");
			} else {
				model.addAttribute("message", "Tài khoản đã tồn tại");
				return new ModelAndView("/admin/users/addOrEdit", model);
			}
		} else {
			Optional<Tb_User> otp = userService.findById(dto.getId());
			entity = otp.get();
			BeanUtils.copyProperties(dto, entity);
			entity.setCreateDate(entity.getCreateDate());
			entity.setUpdateDate(new Date());
			userService.save(entity);
			model.addAttribute("message", "Cập nhập tài khoản thành công");
		}

		return new ModelAndView("forward:/admin/users/view/" + entity.getId(), model);
	}

	@GetMapping("delete/{id}")
	public ModelAndView delete(Model model, @PathVariable("id") Integer id) {
		if (userService.findById(id).isPresent()) {
			userService.deleteById(id);
			model.addAttribute("message", "Tài khoản đã được xóa");
			return new ModelAndView("forward:/admin/users");
		}
		model.addAttribute("message", "Không tồn tại tài khoản này");
		return new ModelAndView("forward:/admin/users");
	}

//	@RequestMapping("")
//	public String list(ModelMap model) {
//		List<Tb_User> list = userService.findAll();
//		model.addAttribute("users", list);
//		return "admin/users/list";
//	}
//
//	@GetMapping("search")
//	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
//		List<Tb_User> list = null;
//		if (StringUtils.hasText(name)) {
//			list = userService.findByNameContaining(name);
//		} else {
//			list = userService.findAll();
//		}
//
//		model.addAttribute("users", list);
//
//		return "admin/users/search";
//	}

	@GetMapping("")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
		Page<Tb_User> resultPage = null;

		if (StringUtils.hasText(name)) {
			resultPage = userService.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = userService.findAll(pageable);
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

		model.addAttribute("userPage", resultPage);

		return "admin/users/searchPaginated";
	}

}
