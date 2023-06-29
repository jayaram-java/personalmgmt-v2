package com.company.Personalmgmt.service;

import java.util.List;

import com.company.Personalmgmt.dto.UserDto;

public interface UserService {
	
	boolean saveuserdetails(UserDto userDto);
	
	List<UserDto> getalluserdetails();
	
	UserDto getEmployeeDetailsFromid(Long id);
	
	boolean deleteEmployeeDetailsFromid(Long id);
	
	boolean checkUsername(String name);
	
	UserDto getspecifieduserdetail(String name);
	
	List<UserDto> currentUserDetails();

}
