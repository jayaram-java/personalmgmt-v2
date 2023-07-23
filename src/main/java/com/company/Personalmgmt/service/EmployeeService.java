package com.company.Personalmgmt.service;

import java.util.List;

import com.company.Personalmgmt.dto.EmployeeDto;

public interface EmployeeService {

	List<EmployeeDto> getAllEmployeeTask();

	boolean saveEmployeeDetails(EmployeeDto employeeDto);

	EmployeeDto getEmployeeTaskFromid(Long id);

	boolean deleteEmployeeTaskFromid(Long id);
	
	List<EmployeeDto> getTaskDetailsBasedonProject(String project);

}
