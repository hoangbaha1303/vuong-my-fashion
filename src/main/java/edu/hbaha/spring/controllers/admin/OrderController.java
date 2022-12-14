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

import edu.hbaha.spring.domain.Tb_Order;
import edu.hbaha.spring.domain.Tb_OrderDetail;
import edu.hbaha.spring.domain.Tb_ProductCategory;
import edu.hbaha.spring.domain.Tb_User;
import edu.hbaha.spring.service.EmailService;
import edu.hbaha.spring.service.OrderDetailService;
import edu.hbaha.spring.service.OrderService;
import edu.hbaha.spring.service.UserService;
import groovyjarjarpicocli.CommandLine.Model;

@Controller
@RequestMapping("admin/orders")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
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
				pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("orderDate").descending());
				break;
			case 2: 
				pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("orderDate"));
				break;
			case 3: 
				pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("status").descending());
				break;
			case 4: 
				pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("status"));
				break;
		}
		model.addAttribute("sort", sortPage);		
		Page<Tb_Order> resultPage = orderService.findAll(pageable);
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
		
		Map<Integer, BigDecimal> total = new HashMap<>();
		for(Tb_Order x : resultPage) {
			total.put(x.getOrderID(), orderDetailService.getAmount(x.getOrderID()));
		}
		model.addAttribute("total", total);
		
		model.addAttribute("orderPage", resultPage);

		return "admin/orders/searchPaginated";
	}
	
	@RequestMapping("detail/{orderID}")
	public String getDetail(ModelMap model,@PathVariable("orderID") Integer orderID) {
		Tb_Order order = orderService.findById(orderID).get();
		Tb_User user  = userService.findById(order.getCustomerID()).get();
		model.addAttribute("user", user);
		model.addAttribute("order", order);
		model.addAttribute("total", orderDetailService.getAmount(orderID));
		
		model.addAttribute("orderDetails", orderDetailService.findByid_OrderID(orderID));
		return "admin/orders/view";
	}
	
	@GetMapping("edit/{orderID}")
	public String getEdit(ModelMap model,@PathVariable("orderID") Integer orderID) {
		Tb_Order order = orderService.findById(orderID).get();
		model.addAttribute("order", order);
		model.addAttribute("total", orderDetailService.getAmount(orderID));
		
		model.addAttribute("orderDetails", orderDetailService.findByid_OrderID(orderID));
		return "admin/orders/edit";
	}
	
	@PostMapping("update")
	public String postUpdate(ModelMap model,@RequestParam("orderID") Integer orderID, @RequestParam("status") int status) {
		Tb_Order order = orderService.findById(orderID).get();
		order.setStatus(status);
		Tb_User user  = userService.findById(order.getCustomerID()).get();
		List<Tb_OrderDetail> orderdetail = orderDetailService.findByid_OrderID(orderID);
		String body = "M?? ????n H??ng: "+orderID+"\n";
		for(Tb_OrderDetail x: orderdetail) {
			body += "S???n Ph???m: "+x.getProductName()+" - S??? L?????ng: "+x.getQuantity()+" - Gi??: "+x.getPrice()+"\n";
		}
		body += "T???ng Thanh To??n: "+ orderDetailService.getAmount(orderID);
		if(status == 2) {
			emailService.sendMail(user.getEmail(), "????n H??ng #"+orderID+" ??ang ???????c g???i t???i b???n", body);
		}
		if(status == 3) {
			order.setDeliveryDate(new Date());
			emailService.sendMail(user.getEmail(), "???? giao h??ng th??nh c??ng #"+orderID,"Ch??n th??nh c???m ??n qu?? kh??ch ???? ???ng h??? cho V????ng M??? Fashion\n"+ body+"\nH??y ????? l???i ????nh gi?? s???n ph???m cho ch??ng t??i ????? s???n ph???m ng??y c??ng ch???t l?????ng h??n nh??. >_<");
		}
		orderService.save(order);
		model.addAttribute("message", "C???p Nh???p Tr???ng Th??i ????n H??ng Th??nh C??ng");
		return "forward:/admin/orders/detail/"+order.getOrderID();
	}
	
}
