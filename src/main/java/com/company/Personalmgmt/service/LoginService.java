package com.company.Personalmgmt.service;

public interface LoginService {

	boolean loginCheck(String username, String password);

	boolean employeeLoginCheck(String username, String password);
	
	
	boolean checkPassword(String password);
	
	boolean changePassword(String newPassword,String currentPassword);

}
