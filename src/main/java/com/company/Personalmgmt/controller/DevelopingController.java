package com.company.Personalmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.dto.PropertyDto;
import com.company.Personalmgmt.service.DevelopingService;

@Controller
@RequestMapping(value = "/developing")
@PropertySource("classpath:config.properties")
public class DevelopingController {

	@Autowired
	private Environment env;

	@Autowired
	DevelopingService developingService;

	@Value("${username}")
	private String userName;

	@Value("${password}")
	private String password;

	@RequestMapping(value = "/createissuejira")
	@ResponseBody
	public boolean createissueatjira() {

		developingService.createIssueAtJira();

		return true;

	}

	@RequestMapping(value = "/savequotesdetails")
	@ResponseBody
	public boolean savePropertyDetails(@RequestBody PropertyDto propertyDto) {

		boolean response = developingService.savePropertyDetails(propertyDto);

		return response;
	}

	@RequestMapping(value = "/configpropertycheck")
	@ResponseBody
	public boolean configPropertyCheck() {

		String usernamey = env.getRequiredProperty("username");

		String passwordy = env.getRequiredProperty("password");

		String usernamez = null;

		String passwordz = null;

		try {

			usernamez = usernamey;

			passwordz = passwordy;

			System.out.println(usernamez + "  " + passwordz);

		} catch (Exception e) {
			System.out.println(e);
		}

		return true;
	}

	@RequestMapping(value = "/checkjpa")
	@ResponseBody
	public boolean checkJPA() {

		boolean response = developingService.checkingJPARepository();

		return true;
	}
	
	public boolean checkJava8(){
		
		developingService.completableFutureJava8();
		
		return true;
	}

}
