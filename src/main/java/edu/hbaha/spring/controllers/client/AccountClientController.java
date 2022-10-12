package edu.hbaha.spring.controllers.client;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.hbaha.spring.domain.Tb_Order;
import edu.hbaha.spring.domain.Tb_User;
import edu.hbaha.spring.models.CategoryDto;
import edu.hbaha.spring.models.ChangePasswordDto;
import edu.hbaha.spring.models.UserDto;
import edu.hbaha.spring.models.UserEditDto;
import edu.hbaha.spring.service.CategoryService;
import edu.hbaha.spring.service.OrderDetailService;
import edu.hbaha.spring.service.OrderService;
import edu.hbaha.spring.service.UserService;

@Controller
@RequestMapping("account")
public class AccountClientController {
	@Autowired
	HttpSession session;
	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
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
	
	@ModelAttribute("account")
	public Tb_User getAccount() {
		Optional<Tb_User> user = userService.findByUserName(session.getAttribute("username").toString());
		return user.get();
	}
	
	@ModelAttribute("countOrder")
	public int getCountOrder() {
		List<Tb_Order> list = orderService.findByCustomerID(getAccount().getId());
		if(list.isEmpty()) {
			return 0;
		} else
		return list.size();
	}
	
	@GetMapping("")
	public String getAccount(ModelMap model) {
		if (session.getAttribute("username") == null) {
			return "redirect:/login";
		}
		
		List<Tb_Order> orders = orderService.findByCustomerID(getAccount().getId());
		Map<Integer, BigDecimal> total = new HashMap<>();
		for(Tb_Order x : orders) {
			total.put(x.getOrderID(), orderDetailService.getAmount(x.getOrderID()));
		}
		model.addAttribute("total", total);
		model.addAttribute("orders", orders);
		return "client/account/index";
	}

	@GetMapping("orders/detail/{orderID}")
	public String getOrderDetail(ModelMap model,@PathVariable("orderID") Integer orderID) {
		if (session.getAttribute("username") == null) {
			return "redirect:/login";
		}
		Tb_Order order = orderService.findById(orderID).get();
		model.addAttribute("order", order);
		model.addAttribute("total", orderDetailService.getAmount(orderID));
		model.addAttribute("orderDetails", orderDetailService.findByid_OrderID(orderID));
		return "client/account/orderDetail";
	}
	
	@GetMapping("myOrders")
	public String getMyOrder(ModelMap model, @RequestParam("date") Optional<Integer> date) {
		if (session.getAttribute("username") == null) {
			return "redirect:/login";
		}
 		int dateFind = date.orElse(-5);
		Optional<Tb_User> user = userService.findByUserName(session.getAttribute("username").toString());
		List<Tb_Order> list = orderService.findByCustomerIDAndOrderDateBetween(user.get().getId(), DateUtils.addDays(new Date(), dateFind), new Date());
		model.addAttribute("date", dateFind);
		model.addAttribute("list", list);
		return "client/account/myOrders";
	}
	
	@RequestMapping("detail")
	public String getUserDetail(ModelMap model) {
		if (session.getAttribute("username") == null) {
			return "redirect:/login";
		}
		Optional<Tb_User> user = userService.findByUserName(session.getAttribute("username").toString());
		model.addAttribute("account", user.get());
		return "client/account/userDetail";
	}

	@GetMapping("edit")
	public ModelAndView getEdit(ModelMap model) {
		if (session.getAttribute("username") == null) {
			return new ModelAndView("redirect:/login");
		}

		Optional<Tb_User> otp = userService.findByUserName(session.getAttribute("username").toString());
		UserDto dto = new UserDto();

		Tb_User entity = otp.get();
		BeanUtils.copyProperties(entity, dto);
		model.addAttribute("user", dto);
		dto.setIsEdit(true);
		return new ModelAndView("client/account/edit", model);

	}

	@PostMapping("update")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("user") UserEditDto dto, BindingResult result) {
		if (session.getAttribute("username") == null) {
			return new ModelAndView("redirect:/login");
		}

		if (result.hasErrors()) {
			return new ModelAndView("client/account/edit");
		}
		if (dto.getIsEdit()) {
			Optional<Tb_User> otp = userService.findByUserName(session.getAttribute("username").toString());
			Tb_User entity = otp.get();
			BeanUtils.copyProperties(dto, entity);
			entity.setPassword("");
			entity.setCreateDate(entity.getCreateDate());
			entity.setUpdateDate(new Date());
			userService.save(entity);
			model.addAttribute("message", "Cập nhập tài khoản thành công");
		}

		return new ModelAndView("forward:/account/detail", model);
	}

	@GetMapping("changePassword")
	public String getChangePassword(ModelMap model) {
		if (session.getAttribute("username") == null) {
			return "redirect:/login";
		}
		model.addAttribute("user", new ChangePasswordDto());
		return "client/account/editPassword";
	}

	@PostMapping("changePassword")
	public ModelAndView changePassword(ModelMap model, @Valid @ModelAttribute("user") ChangePasswordDto dto,
			BindingResult result) {
		if (session.getAttribute("username") == null) {
			return new ModelAndView("redirect:/login");
		}

		if (result.hasErrors()) {
			return new ModelAndView("client/account/editPassword");
		}
		Optional<Tb_User> otp = userService.findByUserName(session.getAttribute("username").toString());
		Tb_User entity = otp.get();
		if(!userService.isEqualPassword(dto.getOldPassword(), entity.getPassword())) {
			model.addAttribute("message", "Mật khẩu cũ không đúng! Vui lòng thử lại");
			return new ModelAndView("client/account/editPassword");
		}
		if(!dto.getNewPassword().equals(dto.getConfirmPassword())) {
			model.addAttribute("message", "Mật khẩu xác nhận không khớp mật khẩu mới! Vui lòng thử lại");
			return new ModelAndView("client/account/editPassword");
		}
		entity.setPassword(dto.getNewPassword());
		entity.setCreateDate(entity.getCreateDate());
		entity.setUpdateDate(new Date());
		userService.save(entity);
		model.addAttribute("message", "Thay đổi mật khẩu thành công");

		return new ModelAndView("forward:/account/detail", model);
	}
}
