package com.poly.dax.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {
		model.addAttribute("msg","Vui lòng đăng nhập!");
		return "login";
	}
	
	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model) {
//		String role =  authResult.getAuthorities().toString();
//		System.out.println("role: "+role);
//		
//		if(role.contains("admin")){
//	         response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin/blog"));                            
//	         }
		model.addAttribute("msg","Đăng nhập thành công!");
		return "redirect:/home/index";
	}
	
	@RequestMapping("/security/login/error")
	public String loginError(Model model) {
		model.addAttribute("msg","Sai thông tin đăng nhập!");
		return "login";
	}
	
	@RequestMapping("/security/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("msg","Không có quyền truy xuất!");
		return "login";
	}
	
	@RequestMapping("/security/logoff/success")
	public String logoffSuccess(Model model) {
		
		model.addAttribute("msg","Bạn đã đăng xuất!");
		return "login";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
	}
}
