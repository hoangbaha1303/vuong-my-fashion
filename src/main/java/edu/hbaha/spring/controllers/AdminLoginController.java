package edu.hbaha.spring.controllers;

import java.time.LocalDate;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hbaha.spring.domain.Tb_User;
import edu.hbaha.spring.models.AdminLoginDto;
import edu.hbaha.spring.service.OrderService;
import edu.hbaha.spring.service.UserService;

@Controller
public class AdminLoginController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private HttpSession session;

	@RequestMapping("admin")
	public String admin(ModelMap model) {
		
		model.addAttribute("day", model);
		return "/admin/dashboard/index";
	}

	@GetMapping("alogin")
	public String login(ModelMap model) {
		model.addAttribute("user", new AdminLoginDto());
		return "/admin/users/adminLogin";
	}

	@PostMapping("alogin")
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("user") AdminLoginDto dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("/admin/users/adminLogin", model);
		}
		Tb_User user = userService.login(dto.getUserName(), dto.getPassword());
		if (user == null) {
			model.addAttribute("message", "Tài khoản hoặc mật khẩu không đúng");
			return new ModelAndView("/admin/users/adminLogin", model);
		} else {
			if(!user.getGroupID().equals(1)) {
				model.addAttribute("message", "Tài khoản của bạn không đủ quyền hạn");
				return new ModelAndView("client/404/index", model);
			} 
			session.setAttribute("username", user.getUserName());
			Object ruri = session.getAttribute("redirect-uri");

			if (ruri != null) {
				session.removeAttribute("redirect-uri");
				return new ModelAndView("redirect:" + ruri);
			}
			model.addAttribute("message", "Đăng nhập thành công");
			return new ModelAndView("forward:/admin", model);
		}
	}
	
}
