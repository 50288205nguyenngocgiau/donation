package com.poly.dax.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Oauth2LoginController {
	@GetMapping("/welcome")
	public String welcome(Model m) {
		m.addAttribute("msg","Welcome to Spring boot");
		return "welcome";
	}
	@GetMapping("/user")
	public String user(Principal principal, Model m) {
		System.out.println("username: "+principal.getName());
		
		m.addAttribute("msg", principal.getName());
		return "user";
	}
}
