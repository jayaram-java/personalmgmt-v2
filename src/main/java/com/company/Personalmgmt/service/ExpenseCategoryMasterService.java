package com.company.Personalmgmt.service;

import java.util.List;

import com.company.Personalmgmt.dto.ExpenseCategoryDto;
import com.company.Personalmgmt.model.Permission;

public interface ExpenseCategoryMasterService {

	boolean saveExpenseCategoryDetails(ExpenseCategoryDto expenseCategoryDto);

	List<ExpenseCategoryDto> getAllCategoryDetails();

	List<Permission> getAllPermissiondetails();

}
