package com.cubic.apartmentsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String loadLandingPage(Model model) {
		return "employee/home";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String saveEmployeeForm(Model model) {
		return "employee/home";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveEmployee(Model model) {
		return "employee/home";
	}
}
