package com.company.Personalmgmt.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.dto.ExpenseCategoryDto;
import com.company.Personalmgmt.model.ExpenseCategory;
import com.company.Personalmgmt.model.Permission;
import com.company.Personalmgmt.repository.ExpenseCategoryRepository;
import com.company.Personalmgmt.service.ExpenseCategoryMasterService;

@Controller
public class ExpenseCategoryMasterController {

	@Autowired
	ExpenseCategoryRepository expenseCategoryRepository;

	@Autowired
	ExpenseCategoryMasterService expenseCategoryMasterService;
	
	@Autowired
	HttpSession httpsession;


	@RequestMapping(value = "/saveexpensecategorydetails")
	@ResponseBody
	public boolean saveExpenseCategoryDetails(@RequestBody ExpenseCategoryDto expenseCategoryDto) {

		boolean response = expenseCategoryMasterService.saveExpenseCategoryDetails(expenseCategoryDto);

		return response;
	}

	@RequestMapping(value = "/getallexpensecategory")
	@ResponseBody
	public List<ExpenseCategory> getExpenseCategory() {
		
		long userId = (Long) httpsession.getAttribute("userId");

		List<ExpenseCategory> expenseCategory = expenseCategoryRepository.findByPermissionIdOrUserId(1,userId);

		return expenseCategory;
	}

	@RequestMapping(value = "/getallexpensecategorydetails")
	@ResponseBody
	public List<ExpenseCategoryDto> getAllCategoryDetails() {

		List<ExpenseCategoryDto> expenseCategoryDtos = expenseCategoryMasterService.getAllCategoryDetails();

		return expenseCategoryDtos;
	}

	@RequestMapping(value = "/getallpermissiondetails")
	@ResponseBody
	public List<Permission> getAllPermissionDetails() {

		List<Permission> permission = expenseCategoryMasterService.getAllPermissiondetails();

		return permission;
	}

}
