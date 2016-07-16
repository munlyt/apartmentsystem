package com.cubic.apartmentsystem.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cubic.apartmentsystem.domain.User;
import com.cubic.apartmentsystem.exception.BusinessException;
import com.cubic.apartmentsystem.service.IUserService;
import com.cubic.apartmentsystem.util.ServiceResponse;
import com.cubic.apartmentsystem.util.Utility;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	IUserService userService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse> getUser(@PathVariable("id") Long id) {
		try {
			logger.info("Fetching User with id " + id);
			User user = userService.getUserById(id);
			ServiceResponse serviceResponse = new ServiceResponse("User has been retrieved successfully");
			serviceResponse.addParam("user", user);
			return new ResponseEntity<ServiceResponse>(serviceResponse, HttpStatus.OK);
		} catch (Exception e) {
			Utility.logError(logger, "Error Occurred during get user ", e);
			HttpHeaders httpHeaders = ServiceResponse.generateRuntimeErrors(e);
			return new ResponseEntity<ServiceResponse>(httpHeaders, HttpStatus.EXPECTATION_FAILED);
		}

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse> createUser(@Valid @RequestBody User user, BindingResult result) {
		try {
			if (result.hasErrors()) {
				String errorString = "";
				for (Object object : result.getAllErrors()) {
					if (object instanceof FieldError) {
						FieldError fieldError = (FieldError) object;
						errorString += fieldError.getDefaultMessage() + "</br>";
					}
					/*
					 * if(object instanceof ObjectError) { ObjectError
					 * objectError = (ObjectError) object;
					 * System.out.println(objectError.getDefaultMessage());
					 * errorString += objectError.getDefaultMessage()+"\n"; }
					 */
				}
				throw new BusinessException("VLD000", errorString);
			} else {
				logger.info("Creating User " + user.getUsername());
				userService.saveEmployee(user);
				ServiceResponse serviceResponse = new ServiceResponse("User has been saved successfully");
				serviceResponse.addParam("user", user);
				return new ResponseEntity<ServiceResponse>(serviceResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			Utility.logError(logger, "Error Occurred during create user ", e);
			HttpHeaders httpHeaders = ServiceResponse.generateRuntimeErrors(e);
			return new ResponseEntity<ServiceResponse>(httpHeaders, HttpStatus.EXPECTATION_FAILED);
		}

	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ServiceResponse> updateUser(@RequestBody User user) {
		try {
			logger.info("Updating User " + user.getUsername());
			userService.updateUser(user);
			ServiceResponse serviceResponse = new ServiceResponse("User has been updated successfully");
			serviceResponse.addParam("user", user);
			return new ResponseEntity<ServiceResponse>(serviceResponse, HttpStatus.OK);

		} catch (Exception e) {
			Utility.logError(logger, "Error Occurred during update user ", e);
			HttpHeaders httpHeaders = ServiceResponse.generateRuntimeErrors(e);
			return new ResponseEntity<ServiceResponse>(httpHeaders, HttpStatus.EXPECTATION_FAILED);
		}

	}

	@RequestMapping(value = "{id}/verify-email/{verificationCode}", method = RequestMethod.GET)
	public String verifyEmailAddress(@PathVariable("verificationCode") String verificationCode,
			@PathVariable("id") Integer userId) {
		try {
			// userService.verifyUser(userId, verificationCode);
			return "redirect:/login-signup";

		} catch (Exception e) {
			Utility.logError(logger, "Error Occurred during update user ", e);
			return "redirect:/accessdenied";
		}

	}
}