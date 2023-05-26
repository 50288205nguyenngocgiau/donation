package com.poly.dax.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.dax.service.BlogService;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
    private BlogService blogService;
	@Autowired
	HttpServletRequest request;

    @RequestMapping("/index")
    public String home(Model model){
        model.addAttribute("list", blogService.getAllBlog());
        return "main";
    }
	@GetMapping("/main2")
	public String index2() {
		return "main2";
	}
	@GetMapping("/demo")
	public String demo() {
		return "login";
	}
	@RequestMapping("/admin")
	public String admin(Model m) {
		if(!request.isUserInRole("admin")) {
			return "redirect:/auth/access/denied";
		}
		m.addAttribute("msg","admin success");
		return "admin";
	}
	@RequestMapping("/user")
	public String user(Model m) {
		if(!(request.isUserInRole("admin") || request.isUserInRole("user"))) {
			return "redirect:/auth/access/denied";
		}
		m.addAttribute("msg","user success");
		return "user";
	}
	@RequestMapping("/authenticated")
	public String authenticated(Model m) {
		if(request.getRemoteUser()==null) {
			return "reirect:/auth/login/form";
		}
		m.addAttribute("msg","authenticated success");
		return "login";
	}
	@RequestMapping("/auth/access/denied")
	public String denied(Model m) {
		m.addAttribute("msg","Bạn không có quyền truy cập");
		return "login";
	}
}
