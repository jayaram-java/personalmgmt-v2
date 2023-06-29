package com.company.Personalmgmt.service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;

import com.company.Personalmgmt.dto.ExpenseDto;
import com.company.Personalmgmt.model.ExpenseDetails;

public interface ExpenseService {

	boolean saveExpenseDetails(ExpenseDto expenseDto);

	List<ExpenseDto> getAllExpenseDetails();

	ExpenseDto getExpenseDetailsById(Long id);

	List<ExpenseDto> getExpenseDetailsForGraph();
	
	List<ExpenseDto> getExpenseDetailsForGraphSearch(String year);
	
	List<ExpenseDto> getFinancialYearExpenseDetailsForGraph();
	
	List<ExpenseDto> getFinancialYearExpenseDetailsForGraphFilter(String year);

	List<ExpenseDto> getExpenseDetailsForMonthGraph();

	List<ExpenseDto> getExpenseDetailBasedOnDate();

	List<ExpenseDto> getExpenseDetailBasedPaymentMode();
	
	List<ExpenseDto> getExpenseDetailBasedPaymentModeSearch(String year);

	List<ExpenseDto> getExpenseDetailBasedpreviousmonth();
	
	List<ExpenseDto> getExpenseDetailBasedpreviousmonthForEmailScheduling(long userid);

	List<ExpenseDto> getExpenseDetailBasedCategoryPreviousMonth();
	
	List<ExpenseDto> getExpenseDetailBasedCategoryPreviousMonthForEmailScheduling(long userid);

	String getAverageAmount();

	List<ExpenseDto> getExpenseReport(String year, String month, String category);
	
	// java 8
	
	List<ExpenseDetails> getExpenseDetailsBasedOnCategory(String year, Predicate<ExpenseDetails> expensedetailz );
	
	Page<ExpenseDetails> findExpenseDetailsBasedOnPagination(int offset,int pagesize);
	
	// using stream
	
	List<ExpenseDetails> getExpenseDetailswithPriceRange(String year, String category,Double price);
	
	List<ExpenseDetails> getExpenseDetailswithPriceRangeBetween(String year, String category, Integer minPrice,Integer maxPrice);
	
	

}
