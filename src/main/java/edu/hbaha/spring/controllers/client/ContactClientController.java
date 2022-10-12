package edu.hbaha.spring.controllers.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.hbaha.spring.domain.Tb_FeedBack;
import edu.hbaha.spring.models.CategoryDto;
import edu.hbaha.spring.models.ChangePasswordDto;
import edu.hbaha.spring.models.FeedBackDto;
import edu.hbaha.spring.service.AboutService;
import edu.hbaha.spring.service.CategoryService;
import edu.hbaha.spring.service.ContactService;
import edu.hbaha.spring.service.EmailService;
import edu.hbaha.spring.service.FeedBackService;

@Controller
public class ContactClientController {
	@Autowired
	ContactService contactService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	FeedBackService feedBackService;
	
	@Autowired
	AboutService aboutService;
	
	@Autowired
	EmailService emailService;
	
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
	
	@RequestMapping("contact")
	public String getContact(ModelMap model){
		model.addAttribute("contact", contactService.getById(1));
		model.addAttribute("feedback", new FeedBackDto());
		return "client/contact/index";
	}
	
	@PostMapping("feedback")
	public ModelAndView feedback(ModelMap model, @Valid @ModelAttribute("feedback") FeedBackDto dto,
			BindingResult result) {
		if (session.getAttribute("username") == null) {
			return new ModelAndView("redirect:/login");
		}

		if (result.hasErrors()) {
			model.addAttribute("feedback", dto);
			return new ModelAndView("client/contact/index");
		}
		
		Tb_FeedBack entity = new Tb_FeedBack();
		BeanUtils.copyProperties(dto, entity);
		entity.setUsername(session.getAttribute("username").toString());
		emailService.sendMail(contactService.getById(1).getEmail(), entity.getSubject(),"Người dùng: "+entity.getUsername()+" gửi phản ánh đến hệ thống của bạn.\n"+ entity.getContent());
		feedBackService.save(entity);
		model.addAttribute("message", "Gửi phản ánh thành công");
		return new ModelAndView("forward:/contact", model) ;
	}
	
	@GetMapping("aboutUs")
	public String aboutUs(ModelMap model) {
		model.addAttribute("about", aboutService.findById(1).get());
		model.addAttribute("feedbacks", feedBackService.findByStatus(true));
		return "client/aboutUs/index";
	}
}
