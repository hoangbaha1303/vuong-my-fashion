package edu.hbaha.spring.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.hbaha.spring.domain.Tb_About;
import edu.hbaha.spring.service.AboutService;

@Controller
@RequestMapping("admin/about")
public class AboutController {
	@Autowired
	AboutService aboutService;
	
	@GetMapping("")
	public String getAboutEdit(ModelMap model) {
		Tb_About about = aboutService.findById(1).get();
		model.addAttribute("description", about.getDescription());
		model.addAttribute("detail", about.getDetail());
		return "admin/about/addOrEdit";
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView postUpdate(ModelMap model,@RequestParam("description") String description, @RequestParam("detail") String detail) {
		Tb_About about = aboutService.findById(1).get();
		about.setDetail(detail);
		about.setDescription(description);
		aboutService.save(about);
		model.addAttribute("message","Cập nhập thông tin website thành công");
		return new ModelAndView("/admin/dashboard/index", model);
	}
}
