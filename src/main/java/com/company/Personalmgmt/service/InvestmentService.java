/**
 * 
 */
package com.company.Personalmgmt.service;

import java.util.Map;

/**
 * This class is used for
 *
 * @author Jayaram
 */

public interface InvestmentService {

	Map<String, Object> getAllDepositDetails();
	
	Map<String, Object> getDepositDetailsById(String accountNo);

}
