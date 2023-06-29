package com.company.Personalmgmt.service;

import java.util.List;

import com.company.Personalmgmt.dto.SecondaryUserDetailsDto;

public interface EmailService {

	boolean sendEmailForExpense();

	List<SecondaryUserDetailsDto> getAllEmailAddress();

	boolean sendEmailForCheckList();
	
	void sendEmailToAll();
	
	boolean sendEmailtoClients(String toAddress);
	
	boolean sendEmailForScheduling();
	
	boolean sendEmailChecking();

}
