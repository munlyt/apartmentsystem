package com.cubic.apartmentsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String loadLandingPage(Model model) {
		return "admin/home";
	}
}
