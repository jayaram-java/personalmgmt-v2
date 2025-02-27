/**
 * 
 */
package com.company.Personalmgmt.service;

import java.util.Map;

import com.company.Personalmgmt.dto.DepositAccountDetailsDto;

/**
 * This class is used for
 *
 * @author Jayaram
 */

public interface InvestmentService {

	Map<String, Object> getAllDepositDetails();

	Map<String, Object> getDepositDetailsById(String accountNo);

	Map<String, Object> getDepositDetails(String year, String bankName);

	boolean saveDepositDetails(DepositAccountDetailsDto depositAccountDetailsDto);

}
