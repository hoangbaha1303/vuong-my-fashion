package edu.hbaha.spring.controllers.admin;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.hbaha.spring.domain.Tb_FeedBack;
import edu.hbaha.spring.domain.Tb_Order;
import edu.hbaha.spring.domain.Tb_OrderDetail;
import edu.hbaha.spring.domain.Tb_ProductCategory;
import edu.hbaha.spring.domain.Tb_User;
import edu.hbaha.spring.service.EmailService;
import edu.hbaha.spring.service.FeedBackService;
import edu.hbaha.spring.service.OrderDetailService;
import edu.hbaha.spring.service.UserService;
import groovyjarjarpicocli.CommandLine.Model;

@Controller
@RequestMapping("admin/feedbacks")
public class FeedBackController {
	@Autowired
	FeedBackService feedBackService;

	@Autowired
	EmailService emailService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("")
	public String search(ModelMap model, @RequestParam("sort") Optional<Integer> sort,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		int sortPage = sort.orElse(1);
		
		Pageable pageable = null;
		switch(sortPage) {
			case 1:
				pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("createDate").descending());
				break;
			case 2: 
				pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("createDate"));
				break;
			case 3: 
				pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("status").descending());
				break;
			case 4: 
				pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("status"));
				break;
		}
		model.addAttribute("sort", sortPage);
		Page<Tb_FeedBack> resultPage = feedBackService.findAll(pageable);
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

		model.addAttribute("feedBackPage", resultPage);
		return "admin/feedbacks/searchPaginated";
	}
	
	@RequestMapping("detail/{id}")
	public String getDetail(ModelMap model,@PathVariable("id") Integer id) {
		Tb_FeedBack feedback = feedBackService.findById(id).get();
		Tb_User user = userService.findByUserName(feedback.getUsername()).get();
		model.addAttribute("feedback", feedback);
		model.addAttribute("user", user);
		return "admin/feedbacks/view";
	}
	
	@GetMapping("edit/{id}")
	public String getEdit(ModelMap model,@PathVariable("id") Integer id) {
		Tb_FeedBack feedback = feedBackService.findById(id).get();
		model.addAttribute("feedback", feedback);
		return "admin/feedbacks/edit";
	}
	
	@PostMapping("update")
	public String postUpdate(ModelMap model,@RequestParam("id") Integer id, @RequestParam("status") boolean status) {
		Tb_FeedBack feedback = feedBackService.findById(id).get();
		feedback.setStatus(status);
		
		feedBackService.save(feedback);
		model.addAttribute("message", "Cập Nhập Trạng Thái Phản Hồi Thành Công");
		return "forward:/admin/feedbacks/detail/"+feedback.getId();
	}
	
}
