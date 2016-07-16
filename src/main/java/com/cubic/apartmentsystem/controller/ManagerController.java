package com.cubic.apartmentsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String loadLandingPage(Model model) {
		return "manager/home";
	}
}
