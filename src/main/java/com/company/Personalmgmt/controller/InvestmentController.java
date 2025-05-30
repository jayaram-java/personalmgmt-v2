/**
 * 
 */
package com.company.Personalmgmt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.dto.DepositAccountDetailsDto;
import com.company.Personalmgmt.dto.KeyNotesDto;
import com.company.Personalmgmt.service.DepositService;
import com.company.Personalmgmt.service.InvestmentService;

/**
 * This class is used for
 *
 * @author Jayaram
 */

@Controller
@RequestMapping("/investment")
public class InvestmentController {

	@Autowired
	InvestmentService investmentService;
	
	@Autowired
	DepositService depositService;

	@RequestMapping(value = "/getAllDetails", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> getAllDepositDetails() {

		Map<String, Object> depositDetials = depositService.getAllDepositDetails();

		return depositDetials;
	}
	
	@RequestMapping(value = "/getDetailsFromId", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> getDepositDetailsById(@RequestParam String accountno) {

		Map<String, Object> depositDetials = investmentService.getDepositDetailsById(accountno);

		return depositDetials;
	}
	
	@RequestMapping(value = "/getDetailsFromBankName", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> getKeyNotesDetailsFromId(@RequestParam String year, @RequestParam String bankName) {

		Map<String, Object> depositDetials = depositService.getAllDepositDetails(bankName);
		
		System.out.println("depositDetials = "+ depositDetials);

		return depositDetials;
	}
	
	@RequestMapping(value = "/saveDepositDetails", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public boolean saveDepositDetails(@RequestBody DepositAccountDetailsDto depositAccountDetailsDto) {

		boolean response = depositService.saveDepositDetails(depositAccountDetailsDto);

		return response;
	}
	
	@RequestMapping(value = "/getDepositDetailsFromId", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public DepositAccountDetailsDto getDepositDetailsFromId(@RequestParam Long id) {

		DepositAccountDetailsDto depositAccountDetailsDto = depositService.getDepositDetailsFromId(id);

		return depositAccountDetailsDto;
	}
	
	

}
