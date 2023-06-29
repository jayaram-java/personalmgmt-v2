package com.company.Personalmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.ExpenseCategory;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {

	ExpenseCategory findByName(String name);
	
	List<ExpenseCategory> findByPermissionIdOrUserId(long permissionId,long userId);
	
	
}
