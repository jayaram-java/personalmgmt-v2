package com.company.Personalmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.ExpenseDetails;

@Repository
public interface ExpenseDetailsRepository extends JpaRepository<ExpenseDetails, Long> {
	
	
	
	

	ExpenseDetails findById(long id);
	
	List<ExpenseDetails> findByYearAndMonthAndUserId(String year, String month,long id);

	List<ExpenseDetails> findByYearAndMonthAndUserIdOrderByIdDesc(String year, String month,long id);
	
	
	
	List<ExpenseDetails> findByYearAndUserId(String year,long id);
	
	List<ExpenseDetails> findByYearAndMonthAndExpenseCategoryNameAndUserId(String year, String month,String categoryName,long id);
	
	List<ExpenseDetails> findByYearAndExpenseCategoryNameAndUserId(String year, String categoryName,long id);
	
	@Query(nativeQuery = true, value = "SELECT i.expenseId FROM expense_detail AS i  ORDER BY i.id DESC LIMIT 1")
	List<Object[]> getexpenseId();
	

	@Query(nativeQuery = true, value = "SELECT i.month_n, i.month, SUM(i.amount) FROM expense_detail AS i WHERE i.year = :curyear AND i.user_id =:id GROUP BY i.month_n")
	List<Object[]> getexpensedetails(@Param("curyear") String curyear,@Param("id") long id);
	
	@Query(nativeQuery = true, value = "SELECT i.month_n, i.month, SUM(i.amount) FROM expense_detail AS i WHERE  i.date BETWEEN :startFy AND :endFy AND i.user_id =:id GROUP BY i.month_n ORDER BY i.month_n")
	List<Object[]> getExpenseDetailsFy(@Param("startFy") String startFy,@Param("endFy") String endFy,@Param("id") long id);
	

	@Query(nativeQuery = true, value = "SELECT j.name ,i.month , SUM(i.amount) FROM expense_detail AS i JOIN expense_category AS j ON i.expense_category_id = j.id WHERE i.month = :curmonth  AND i.year = :curyear AND i.user_id =:id GROUP BY i.expense_category_id  ")
	List<Object[]> getexpensedetailsbasedonmonth(@Param("curmonth") String curmonth,@Param("curyear") String curyear,@Param("id") long id);

	@Query(nativeQuery = true, value = "SELECT i.date , SUM(i.amount) FROM expense_detail AS i WHERE i.date > NOW() - interval 1 WEEK AND i.user_id =:id GROUP BY date")
	List<Object[]> getExpenseListBasedonDay(@Param("id") long id);

	@Query(nativeQuery = true, value = "SELECT i.month_n, i.month ,i.payment_method, sum(i.amount) FROM expense_detail AS i WHERE i.year=:curyear AND i.user_id =:id GROUP BY i.payment_method ,i.month_n  ORDER BY i.month_n")
	List<Object[]> getExpenseListForPaymentMode(@Param("curyear") String curyear,@Param("id") long id);
	
	
	@Query(nativeQuery = true, value = "SELECT i.month , SUM(i.amount) FROM expense_detail AS i JOIN expense_category AS j ON i.expense_category_id = j.id WHERE i.month = :curmonth  AND i.year = :curyear AND i.user_id =:id ")
	List<Object[]> getexpensedetailsbasedonmonthForAvg(@Param("curmonth") String curmonth,@Param("curyear") String curyear,@Param("id") long id);
	
	
	

}
