package com.poly.dax.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dax.entity.Account;
import com.poly.dax.service.AccountService;
import com.poly.dax.store.UserService;

@Controller
public class AccountController {
	@Autowired
	AccountService accountService;
	@Autowired
	BCryptPasswordEncoder en;
	@Autowired
	UserService userService;
	
	@PostMapping("/createAccount")
	public String createAccount(@RequestParam("fullName") String fullname,
			@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,
			@RequestParam("username") String username,
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model m) {
		Date createDate = new Date();
		Account ac = new Account();
		String pw = en.encode(password);
		
		ac.setEmail(email);
		ac.setPassword(pw);
		ac.setUsername(username);
		ac.setFullName(fullname);
		ac.setFirstname(firstname);
		ac.setLastname(lastname);
		ac.setPhone(phone);
		ac.setCreateDate(createDate);
		ac.setAvatar("avatar");
		ac.setBackground("bg");
		
		List<Account> checkEmail = accountService.findEmail();
		boolean emailExists = false;
		
		for(int i=0; i< checkEmail.size(); i++) {
			if(checkEmail.get(i).getEmail().equals(email)) {
				emailExists = true;
				m.addAttribute("msg","Email này đã tồn tại, vui lòng nhập email khác");
				break;
			}
		}
		if(!emailExists) {
			Account createAc = accountService.createAccount(ac);
			if(createAc != null) {
				m.addAttribute("msg", "Register Account Successfully");
			}else {
				m.addAttribute("msg", "Register Account Fail");
			}
			System.out.println("CHECK: "+emailExists);
		}
		
		return "/register";
	}
}
