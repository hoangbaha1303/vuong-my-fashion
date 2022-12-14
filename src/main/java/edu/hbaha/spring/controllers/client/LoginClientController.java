package edu.hbaha.spring.controllers.client;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.hbaha.spring.domain.Tb_ProductCategory;
import edu.hbaha.spring.domain.Tb_User;
import edu.hbaha.spring.models.AdminLoginDto;
import edu.hbaha.spring.models.CategoryDto;
import edu.hbaha.spring.models.UserDto;
import edu.hbaha.spring.service.CategoryService;
import edu.hbaha.spring.service.EmailService;
import edu.hbaha.spring.service.ShoppingCartService;
import edu.hbaha.spring.service.UserService;
import net.bytebuddy.utility.RandomString;

@Controller
public class LoginClientController {
	@Autowired
	UserService userService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	@Autowired
	HttpSession session;
	
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
	
	@GetMapping("login")
	public String getLogin(ModelMap model){
		model.addAttribute("user", new AdminLoginDto());
		session.setAttribute("total", shoppingCartService.getAmount());
		session.setAttribute("num", shoppingCartService.getCount());
		return "client/login/login";
	}

	@PostMapping("login")
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("user") AdminLoginDto dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("client/login/login", model);
		}
		Tb_User user = userService.login(dto.getUserName(), dto.getPassword());
		if (user == null) {
			model.addAttribute("message", "T??i kho???n ho???c m???t kh???u kh??ng ????ng");
			return new ModelAndView("client/login/login", model);
		} else {
			session.setAttribute("username", user.getUserName());
			session.setAttribute("account", userService.findByUserName(user.getUserName()));
			return new ModelAndView("forward:/", model);
		}
	}
	
	@GetMapping("register")
	public String getRegister(ModelMap model) {
		UserDto dto = new UserDto();
		dto.setIsEdit(false);
		model.addAttribute("user", dto);
		return "client/login/register";
	}
	
	@PostMapping("register")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("user") UserDto dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("client/login/register");
		}
		Tb_User entity = null;
		if (!dto.getIsEdit()) {
			Optional<Tb_User> opt = userService.findByUserName(dto.getUserName());
			if(!opt.isPresent()) {
			entity = new Tb_User();
			dto.setId(entity.getId());
			BeanUtils.copyProperties(dto, entity);
			entity.setCreateDate(new Date());
			String password = entity.getPassword();
			userService.save(entity);
			model.addAttribute("message", "T???o t??i kho???n th??nh c??ng");
			emailService.sendMail(entity.getEmail(),"T???O T??I KHO???N TH??NH C??NG", "Ch??c m???ng b???n t???o t??i kho???n th??nh c??ng \n T??i kho???n: "+entity.getUserName()+"\n M???t kh???u: "+password);
			} else {
				model.addAttribute("message", "T??i kho???n ???? t???n t???i");
				return new ModelAndView("client/login/register" , model);
			}
		} else {
			Optional<Tb_User> otp = userService.findById(dto.getId());
			entity = otp.get();
			if(dto.getPassword() == null) {
				dto.setPassword(entity.getPassword());
			}
			BeanUtils.copyProperties(dto, entity);
			entity.setCreateDate(entity.getCreateDate());
			entity.setUpdateDate(new Date());
			userService.save(entity);
			model.addAttribute("message", "C???p nh???p th??ng tin t??i kho???n th??nh c??ng");
		}

		return new ModelAndView("client/login/register" , model);
	}
	
	@GetMapping("forgotPassword")
	public String getForgotPassword(ModelMap model) {
		model.addAttribute("username", "");
		return "client/login/forgotPassword";
	}
	
	@PostMapping("forgotPassword")
	public ModelAndView postForgotPassword(ModelMap model, @RequestParam(name = "username") String username) {
		if (username.equals("")) {
			model.addAttribute("message", "T??i kho???n kh??ng ???????c ????? tr???ng");
			return new ModelAndView("client/login/forgotPassword");
		}
		Optional<Tb_User> opt = userService.findByUserName(username);
		if(opt.isPresent()) {
			String password = RandomStringUtils.randomAlphanumeric(8);
			Tb_User entity = opt.get();
			entity.setPassword(password);
			entity.setUpdateDate(new Date());
			userService.save(entity);
			emailService.sendMail(entity.getEmail(),"?????T L???I M???T KH???U", "M???t kh???u c???a b???n ???? ???????c ?????t l???i l??: "+ password);
			model.addAttribute("message", "M???t kh???u ???? ???????c ?????t l???i th??nh c??ng vui l??ng ki???m tra gmail ????? nh???n l???i m???t kh???u");
		} else {
			model.addAttribute("message", "T??i kho???n b???n nh???p kh??ng ????ng!");
		}
		return new ModelAndView("client/login/forgotPassword", model);
	}
	
	@RequestMapping("/logout")
	public String getLogout() {
		if(session.getAttribute("username") != null)
			session.invalidate();
		return "redirect:/login";
	}
}
