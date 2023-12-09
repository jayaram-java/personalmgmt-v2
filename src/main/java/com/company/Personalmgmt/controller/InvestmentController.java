/**
 * 
 */
package com.company.Personalmgmt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping(value = "/getAllDetails", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> getAllDepositDetails() {

		Map<String, Object> depositDetials = investmentService.getAllDepositDetails();

		return depositDetials;
	}

}
