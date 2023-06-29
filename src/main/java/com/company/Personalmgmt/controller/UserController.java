package com.company.Personalmgmt.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.dto.UserDto;
import com.company.Personalmgmt.service.UserService;

@Controller
@PropertySource("classpath:general.properties")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession httpsession;
	
	@Value("${nextDate}")
	String nextDate;

	@RequestMapping(value = "/getcurrentDateandtime")
	@ResponseBody
	public String getCurrentDateAndTime() {

		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("dd-MMMM-yyyy  'at'  hh:mm a");

		LocalDateTime localDateTime = LocalDateTime.now();

		String ldtString = FOMATTER.format(localDateTime);

		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		//LocalDateTime localDateTime = LocalDateTime.now();
		
		try {

			Date d1 = df.parse(dtf.format(localDateTime));

			Date d2 = df.parse(nextDate);

			long differnce_in_times = d2.getTime() - d1.getTime();

			long difference_in_seconds = (differnce_in_times / 1000) % 60;

			long difference_in_minutes = (differnce_in_times / (1000 * 60)) % 60;

			long difference_in_hours = (differnce_in_times / (1000 * 60 * 60)) % 24;

			// long difference_in_years = (differnce_in_times / (1000l * 60 * 60
			// * 24 * 365));

			long difference_in_days = (differnce_in_times / (1000 * 60 * 60 * 24)) % 365;

			System.out.println(difference_in_days + " days, " + difference_in_hours + " hours, " + difference_in_minutes
					+ " minutes, " + difference_in_seconds + " seconds");

			String remainingTime = "  Remaining Time : "+difference_in_days + " days, " + difference_in_hours + " hours, "
					+ difference_in_minutes + " minutes, " + difference_in_seconds + " seconds";
			
			String joiningContent = ldtString.concat(remainingTime);
			
	        httpsession.setAttribute("Date", joiningContent);

		} catch (ParseException e) {

			e.printStackTrace();
		}

		return ldtString;
	}

	@RequestMapping(value = "/saveuserdetails")
	@ResponseBody
	public boolean saveuserdetails(@RequestBody UserDto userDto) {

		boolean response = userService.saveuserdetails(userDto);

		return response;
	}

	@RequestMapping(value = "/getuserdetails")
	@ResponseBody
	public List<UserDto> getalluserdetails() {

		List<UserDto> userDtos = userService.getalluserdetails();

		return userDtos;
	}
	
	
	@RequestMapping(value = "/getuserdetailsname")
	@ResponseBody
	public UserDto getalluserdetails(String name) {

		UserDto userDtos = userService.getspecifieduserdetail(name);

		return userDtos;
	}

	@RequestMapping(value = "/deleteemployeedetailsfromid")
	@ResponseBody
	public boolean deleteEmployeeDetailsFromid(Long id) {

		boolean response = userService.deleteEmployeeDetailsFromid(id);

		return response;
	}

	@RequestMapping(value = "/getemployeedetailsfromid")
	@ResponseBody
	public UserDto getEmployeeDetailsFromid(Long id) {

		UserDto userDto = userService.getEmployeeDetailsFromid(id);

		return userDto;
	}

	@RequestMapping(value = "/checkusername")
	@ResponseBody
	public boolean checkUsername(String name) {

		boolean response = userService.checkUsername(name);

		return response;
	}
	
	
	@RequestMapping(value = "/showcurrentDateandtime")
	@ResponseBody
	public String showCurrentDateAndTime() {

		Date date = new Date();

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		String currentDate = df.format(date);

		return currentDate;

	}
	
	
	@RequestMapping(value = "/getCurrentUserDetails")
	@ResponseBody
	public List<UserDto> getCurrentUserDetails() {

		List<UserDto> userDto = userService.currentUserDetails();

		return userDto;
	}
	
	
	
	
	
	
	
	
	

}
