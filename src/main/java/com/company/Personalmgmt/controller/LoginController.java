package com.company.Personalmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "/adminloginvalidate")
	@ResponseBody
	public boolean logincheck(String username, String password) {

		boolean name = loginService.loginCheck(username, password);

		return name;
	}
	
	@RequestMapping(value = "/employeeloginvalidate")
	@ResponseBody
	public boolean employeeLoginCheck(String username, String password) {

		boolean response = loginService.employeeLoginCheck(username, password);

		return response;
	}
	
	@RequestMapping(value = "/checkpassword")
	@ResponseBody
	public boolean checkPassword(String password) {

		boolean response = loginService.checkPassword(password);

		return response;
	}
	
	@RequestMapping(value = "/changepassword")
	@ResponseBody
	public boolean changePassword(String newPassword, String currentPassword) {

		boolean response = loginService.changePassword(newPassword, currentPassword);
		
		return response;
	}

}
