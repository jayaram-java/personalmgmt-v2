package com.company.Personalmgmt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.Personalmgmt.dto.ExpenseDto;
import com.company.Personalmgmt.exception.NoDataFoundException;
import com.company.Personalmgmt.model.ExpenseDetails;
import com.company.Personalmgmt.repository.ExpenseCategoryRepository;
import com.company.Personalmgmt.repository.YearMasterRepository;
import com.company.Personalmgmt.service.ExpenseService;

@RestController
public class ExpenseController {

	@Autowired
	ExpenseService expenseService;

	@Autowired
	ExpenseCategoryRepository expenseCategoryRepository;

	@Autowired
	HttpSession httpsession;

	@Autowired
	YearMasterRepository yearMasterRepository;

	@RequestMapping(value = "/saveexpensedetails")
	@ResponseBody
	public boolean saveExpenseDetails(@RequestBody ExpenseDto expenseDto) {

		boolean response = expenseService.saveExpenseDetails(expenseDto);

		return response;
	}

	@RequestMapping(value = "/getexpensedetailsbyid")
	@ResponseBody
	public ExpenseDto getExpenseDetailsById(Long id) {

		ExpenseDto expenseDto = expenseService.getExpenseDetailsById(id);

		return expenseDto;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getallexpensedetails")
	@ResponseBody
	public List<ExpenseDto> getAllExpenseDetails() {

		List<ExpenseDto> expenseDtos = expenseService.getAllExpenseDetails();

		/*
		 * List<ExpenseDto> expenseDtoSortedList = expenseDtos.stream().sorted(new
		 * Comparator<ExpenseDto>() {
		 * 
		 * @Override public int compare(ExpenseDto o1, ExpenseDto o2) {
		 * 
		 * return (int) (o2.getId() - o1.getId()); }
		 * 
		 * }).collect(Collectors.toList());
		 */

		return expenseDtos;

	}

	@RequestMapping(value = "/getallexpensedetailsforgraph")
	@ResponseBody
	public List<ExpenseDto> getExpenseDetailsForGraph() {

		List<ExpenseDto> expenseDtos = expenseService.getExpenseDetailsForGraph();

		return expenseDtos;
	}

	@RequestMapping(value = "/getallexpensedetailsforgraphsearch")
	@ResponseBody
	public List<ExpenseDto> getExpenseDetailsForGraphSearch(@RequestParam String year) {

		List<ExpenseDto> expenseDtos = expenseService.getExpenseDetailsForGraphSearch(year);

		return expenseDtos;
	}

	@RequestMapping(value = "/getallexpensedetailsforgraphmonth")
	@ResponseBody
	public List<ExpenseDto> getExpenseDetailsForGraphmonth() {

		List<ExpenseDto> expenseDtos = expenseService.getExpenseDetailsForMonthGraph();

		return expenseDtos;
	}

	@RequestMapping(value = "/getFinancialYearexpensedetailsforgraph")
	@ResponseBody
	public List<ExpenseDto> getFinancialYearExpenseDetailsForGraph() {

		List<ExpenseDto> expenseDtos = expenseService.getFinancialYearExpenseDetailsForGraph();

		return expenseDtos;
	}

	@RequestMapping(value = "/getFinancialYearexpensedetailsforgraphFilter")
	@ResponseBody
	public List<ExpenseDto> getFinancialYearExpenseDetailsForGraphFilter(@RequestParam String year) {

		List<ExpenseDto> expenseDtos = expenseService.getFinancialYearExpenseDetailsForGraphFilter(year);

		return expenseDtos;
	}

	@RequestMapping(value = "/getexpensedetailbasedonday")
	@ResponseBody
	public List<ExpenseDto> getExpenseDetailBasedOnDate() {

		List<ExpenseDto> expenseDtos = expenseService.getExpenseDetailBasedOnDate();

		return expenseDtos;
	}

	@RequestMapping(value = "/getexpensedetailbasedonpaymentmode")
	@ResponseBody
	public List<ExpenseDto> getExpenseDetailBasedOnPayment(@RequestParam String year) {

		List<ExpenseDto> expenseDtos = expenseService.getExpenseDetailBasedPaymentModeSearch(year);

		return expenseDtos;
	}

	@RequestMapping(value = "/getAverageAmount")
	@ResponseBody
	public String getAverageAmount() {

		String output = expenseService.getAverageAmount();

		return output;
	}

	@RequestMapping(value = "/getExpenseReportInitial")
	@ResponseBody
	public List<ExpenseDto> getExpenseReportInitial(String year, String month, String category) {

		List<ExpenseDto> result = new ArrayList<ExpenseDto>();

		httpsession.setAttribute("sumOfExpenseAmount", "0.0");

		return result;

	}

	@RequestMapping(value = "/getExpenseReport")
	@ResponseBody
	public List<ExpenseDto> getExpenseReport(String year, String month, String category) {

		List<ExpenseDto> result = expenseService.getExpenseReport(year, month, category);

		return result;

	}

	// funcational interface

	@RequestMapping(value = "/getExpenseReportYearBased")
	@ResponseBody
	public List<ExpenseDetails> getExpenseReportYearbased(String year, long expenseId) {

		List<ExpenseDetails> expenseDetails = expenseService.getExpenseDetailsBasedOnCategory(year,
				ob -> ob.getExpenseCategory().getId() == expenseId);

		return expenseDetails;

	}

	@GetMapping("/getexpenseDetailsBasedOnPagination/{offset}/{pagesize}")
	public Page<ExpenseDetails> findExpenseDetailsBasedOnPagination(@PathVariable int offset,
			@PathVariable int pagesize) {

		Page<ExpenseDetails> expenseDetails = expenseService.findExpenseDetailsBasedOnPagination(offset, pagesize);

		return expenseDetails;
	}

	@GetMapping("/getexpenseDetailsBasedOnPagination")
	public Page<ExpenseDetails> findExpenseDetailsBasedOnPagination2(@RequestParam int page, @RequestParam int size) {

		Page<ExpenseDetails> expenseDetails = expenseService.findExpenseDetailsBasedOnPagination(page, size);

		return expenseDetails;
	}

	@RequestMapping(value = "/streambasedoutput")
	@ResponseBody
	public List<ExpenseDetails> getExpenseDetailswithPriceRange(String year, String category, Double price) {

		List<ExpenseDetails> expenseDetails = expenseService.getExpenseDetailswithPriceRange(year, category, price);

		return expenseDetails;
	}
	
	@RequestMapping(value = "/expensereportpricebetween")
	@ResponseBody
	public List<ExpenseDetails> getExpenseDetailswithPriceBetween(String year, String category, Integer minPrice,Integer maxPrice) {
		
		if(category.isEmpty()) {
			throw new NoDataFoundException("Data not found");
		}

		List<ExpenseDetails> expenseDetails = expenseService.getExpenseDetailswithPriceRangeBetween(year, category, minPrice, maxPrice);

		return expenseDetails;
	}
	
	
	
	
	
	
	

}
