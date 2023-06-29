package com.company.Personalmgmt.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Personalmgmt.dto.ExpenseCategoryDto;
import com.company.Personalmgmt.model.ExpenseCategory;
import com.company.Personalmgmt.model.Permission;
import com.company.Personalmgmt.model.User;
import com.company.Personalmgmt.repository.ExpenseCategoryRepository;
import com.company.Personalmgmt.repository.PermissionRepository;
import com.company.Personalmgmt.repository.UserRepository;
import com.company.Personalmgmt.service.ExpenseCategoryMasterService;

@Service
public class ExpenseCategoryMasterServiceImpl implements ExpenseCategoryMasterService {
	
	 private static final org.slf4j.Logger log = LoggerFactory.getLogger(ExpenseCategoryMasterServiceImpl.class);
	
	@Autowired
	ExpenseCategoryRepository expenseCategoryRepository;
	
	@Autowired
	PermissionRepository permissionRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public boolean saveExpenseCategoryDetails(ExpenseCategoryDto expenseCategoryDto) {
		
		log.info("API name = *saveExpenseCategoryDetails");

		try {

			if (expenseCategoryDto.getId() == null) {
				
				Permission permission = permissionRepository.findByName(expenseCategoryDto.getPermission());

				ExpenseCategory expenseCategory = new ExpenseCategory();
				expenseCategory.setName(expenseCategoryDto.getName());
				expenseCategory.setPermission(permission);
				expenseCategory.setCreatedDate(new Date());
				expenseCategory.setDescription(expenseCategoryDto.getDescription());
				expenseCategory.setCreatedBy("Admin");
				
				if(expenseCategoryDto.getPermission().equals("private")) {
					
					User user = userRepository.findByUsername(expenseCategoryDto.getUserName());
					
					expenseCategory.setUser(user);
				}
				
				
				expenseCategoryRepository.save(expenseCategory);

			} else {
				Optional<ExpenseCategory> expenseCategoryup = expenseCategoryRepository.findById(expenseCategoryDto.getId());

				BeanUtils.copyProperties(expenseCategoryDto, expenseCategoryup.get());
				expenseCategoryup.get().setModifiedDate(new Date());
				expenseCategoryRepository.save(expenseCategoryup.get());
			}
		} catch (Exception e) {
			log.info("Exception " + e);
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public List<ExpenseCategoryDto> getAllCategoryDetails() {

		List<ExpenseCategoryDto> expenseCategoryDtos = new ArrayList<ExpenseCategoryDto>();

		log.info("API name = *getAllCategoryDetails");

		try {

			List<ExpenseCategory> expenseCategories = expenseCategoryRepository.findAll();

			long n = 1;

			for (ExpenseCategory expenseCategory : expenseCategories) {

				ExpenseCategoryDto expenseCategoryDto = new ExpenseCategoryDto();

				expenseCategoryDto.setSerialId(n);
				expenseCategoryDto.setId(expenseCategory.getId());
				expenseCategoryDto.setName(expenseCategory.getName());
				expenseCategoryDto.setPermission(expenseCategory.getPermission().getName());

				if (expenseCategory.getUser() != null) {

					expenseCategoryDto.setUserName(expenseCategory.getUser().getEmployeename());

				}

				expenseCategoryDtos.add(expenseCategoryDto);
				n++;
			}

		} catch (Exception e) {
			log.info("Exception " + e);
			e.printStackTrace();
		}

		return expenseCategoryDtos;
	}

	@Override
	public List<Permission> getAllPermissiondetails() {

		List<Permission> permissions = permissionRepository.findAll();

		return permissions;
	}

}
