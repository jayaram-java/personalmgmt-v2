/**
 * 
 */
package com.company.Personalmgmt.service;

import java.util.Map;

import com.company.Personalmgmt.dto.DepositAccountDetailsDto;

/**
 * This interface is used for Deposit
 *
 * @author Jayaram
 */

public interface DepositService {

	Map<String, Object> getAllDepositDetails();
	
	Map<String, Object> getAllDepositDetails(String bankName);

	boolean saveDepositDetails(DepositAccountDetailsDto depositAccountDetailsDto);
	
	DepositAccountDetailsDto getDepositDetailsFromId(Long id);
	
	
}
