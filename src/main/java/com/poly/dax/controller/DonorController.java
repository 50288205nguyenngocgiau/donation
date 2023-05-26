package com.poly.dax.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dax.dao.DonorDAO;
import com.poly.dax.entity.Account;
import com.poly.dax.entity.Donor;
import com.poly.dax.service.AccountService;
import com.poly.dax.service.BlogService;
import com.poly.dax.service.DonorService;

@Controller
public class DonorController {
	@Autowired
	AccountService accountService;
	@Autowired
	BlogService blogService;
	@Autowired
	DonorService donorService;
	@Autowired
	DonorDAO dao;
	
	@GetMapping("/checkout")
	public String checkout(Model m,
//			HttpServletRequest request,
			@RequestParam("donated") String donated,
			@RequestParam("id") String id,
			Donor donor, Errors errors,
			@RequestParam("title") String title,
			@AuthenticationPrincipal UserDetails userDetails
			) {
//		String username = request.getRemoteUser();
		String username = userDetails.getUsername();
		Account account = accountService.findById(username);
		String fullname = account.getFullName();
		String phone = account.getPhone();
		Integer idBlog = Integer.parseInt(id);
		Date createDate = new Date();
		Float donate = Float.parseFloat(donated);
		String firstName = account.getFirstname();
		String lastName = account.getLastname();
		
		m.addAttribute("fullname",fullname);
		m.addAttribute("phone",phone);
		m.addAttribute("idBlog",idBlog);
		m.addAttribute("createDate",createDate);
		m.addAttribute("donate",donate);
		m.addAttribute("title",title);
		m.addAttribute("firstName",firstName);
		m.addAttribute("lastName",lastName);
		m.addAttribute("email",username);
		m.addAttribute("id",id);
		
		return "checkout";
	}
	
	@RequestMapping("/checkout")
	public String checkout(
//			Authentication authentication,
			HttpServletRequest request,
			Principal principal ,
			Model m,
			
			@RequestParam("id") String id,
			@RequestParam("donated") String donated
			) {
//		Account account = accountService.findByEmail(SecurityUtils.getPrincipal().getUsername());
//		m.addAttribute("email", account.getEmail());
//		System.out.println("email: "+account.getEmail());
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String email = userDetails.getUsername();
//		 UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
//		 String email = userDetails.getUsername(); 
		
		if (principal == null) {
			
	        return "redirect:/login";
	    }
	    
//		String username = request.getRemoteUser();
		String username = principal.getName();
		Account account = accountService.findById(username);
		String fullname = account.getFullName();
		String phone = account.getPhone();
		Integer idBlog = Integer.parseInt(id);
		Date createDate = new Date();
		Float donate = Float.parseFloat(donated);
		
		Donor donor = new Donor(createDate, donate, fullname, phone, blogService.findBlogById(idBlog),accountService.findById(username),false);
		
		donorService.create(donor);
		
		m.addAttribute("fullname",fullname);
		m.addAttribute("donated",donated);
		m.addAttribute("title",blogService.findBlogById(idBlog).getTitle());
		
		return "/checkoutDetail";
	}
	@GetMapping("/detailDonor")
	public String detailDonor(
			HttpServletRequest request, 
			Model m
			) {
		String email = request.getRemoteUser();
		List<Donor> donorLs = dao.findDonorByAccountId(email);
		
		m.addAttribute("donorLs",donorLs);
		
		return "detailDonor";
	}
	@RequestMapping("/admin/donor")
	public String getAllDonor(Model m,HttpServletRequest request) {
		String username = request.getUserPrincipal().getName();
		if(!username.equals("admin@gmail.com")) {
			return "redirect:/security/unauthoried";
		}
		List<Donor> lsDonor = donorService.findAll();
		
		m.addAttribute("lsDonor",lsDonor);
		return "donor";
	}
	@RequestMapping("/admin/donor/update/{confirm}/{id}")
	public String getAllDonor(Model m, 
			@PathVariable("confirm") Boolean confirm,
			@PathVariable("id") Integer id,
			HttpServletRequest request) {
		String username = request.getUserPrincipal().getName();
		if(!username.equals("admin@gmail.com")) {
			return "redirect:/security/unauthoried";
		}
		List<Donor> lsDonor = donorService.findAll();
		
		Boolean conf = false;
		if(confirm == false) {
			conf = true;
			m.addAttribute("conf", "ok");
		}else if(confirm == true) {
			conf = false;
			m.addAttribute("conf", "Cho");
		}
		donorService.updateConfirm(conf, id);
		
		System.out.println("DONOR UPDATE: "+conf);
		m.addAttribute("lsDonor",lsDonor);
		return "donor";
	}
}
