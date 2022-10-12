package edu.hbaha.spring.controllers.admin;

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


import edu.hbaha.spring.domain.Tb_Contact;
import edu.hbaha.spring.models.ContactDto;

import edu.hbaha.spring.service.ContactService;

@Controller
@RequestMapping("admin/contact")
public class ContactController {
	@Autowired
	ContactService contactService;
	
	@GetMapping("")
	public String getAboutEdit(ModelMap model) {
		Tb_Contact contact = contactService.findById(1).get();
		model.addAttribute("contact", contact);
		return "admin/contact/addOrEdit";
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView postUpdate(ModelMap model, @Valid @ModelAttribute("contact") ContactDto dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("/admin/contact/addOrEdit");
		}
		Tb_Contact contact = contactService.findById(1).get();
		BeanUtils.copyProperties(dto, contact);
		contactService.save(contact);
		
		model.addAttribute("message","Cập nhập thông tin liên lạc thành công");
		return new ModelAndView("/admin/dashboard/index", model);
	}
}
