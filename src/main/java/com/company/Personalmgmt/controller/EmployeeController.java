package com.company.Personalmgmt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.Personalmgmt.dto.EmployeeDto;
import com.company.Personalmgmt.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/employeetasklist")
	@ResponseBody
	public List<EmployeeDto> employeeDetails() {

		List<EmployeeDto> ob = employeeService.getAllEmployeeTask();
		
	//	Collections.reverseOrder((Comparator<EmployeeDto>) ob);

		return ob;

	}

	@RequestMapping(value = "/saveemployeetask")
	@ResponseBody
	public boolean saveEmployeeDetails(@RequestBody EmployeeDto employeeDto) {
		
		boolean response =  employeeService.saveEmployeeDetails(employeeDto);

		return response;
	}
	
	@RequestMapping(value = "/getemployeetaskdetailsfromid")
	@ResponseBody
	public EmployeeDto getEmployeeTaskFromid(@RequestParam Long id) {

		EmployeeDto employeeDto = employeeService.getEmployeeTaskFromid(id);

		return employeeDto;
	}
	
	@RequestMapping(value = "/deleteemployeetaskdetailsfromid")
	@ResponseBody
	public boolean deleteEmployeeTaskFromid(@RequestParam Long id){
		
	  boolean response	= employeeService.deleteEmployeeTaskFromid(id);
	  
	  return response;
	}
	
	
	@RequestMapping(value = "/taskdetailsBasedonProject", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<EmployeeDto> getTaskdetailsBasedonProject(@RequestParam String project) {

		List<EmployeeDto> employeeDtos = employeeService.getTaskDetailsBasedonProject(project);

		return employeeDtos;

	}
	
	
	
	

	@RequestMapping(value = "/employeetasklist123")
	@ResponseBody
	public List<EmployeeDto> employeeDetailsTest() {

		List<EmployeeDto> ob = new ArrayList<EmployeeDto>();

		EmployeeDto employeeDto = new EmployeeDto();

		employeeDto.setName("ram");

		employeeDto.setDescription("spring developer");

		employeeDto.setStartDate("13.01.2021");

		employeeDto.setEndDate("15.01.2021");

		employeeDto.setStatus("active");

		ob.add(employeeDto);

		EmployeeDto employeeDto2 = new EmployeeDto();

		employeeDto2.setName("ganesh");

		employeeDto2.setDescription("react developer");

		employeeDto2.setStartDate("13.01.2021");

		employeeDto2.setEndDate("16.01.2021");

		employeeDto2.setStatus("active");

		ob.add(employeeDto2);

		return ob;

	}

}
