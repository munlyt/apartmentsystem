package com.cubic.apartmentsystem.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cubic.apartmentsystem.component.SessionManager;
import com.cubic.apartmentsystem.enums.RoleType;

@Controller
public class ViewController {
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String welcomePage() {
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null
				&& !SessionManager.isAnonymousUser()) {
			if (SessionManager.getRole().contains(RoleType.ROLE_ADMIN)) {
				return "redirect:/admin/home";
			} else if (SessionManager.getRole().contains(RoleType.ROLE_MANAGER)) {
				return "redirect:/manager/home";
			} else if (SessionManager.getRole().contains(RoleType.ROLE_EMPLOYEE)) {
				return "redirect:/employee/home";
			}
		}
		return "redirect:/";
	}
}
