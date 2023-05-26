package com.poly.dax.store;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUtils {
	public static User getPrincipal() {
		return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
