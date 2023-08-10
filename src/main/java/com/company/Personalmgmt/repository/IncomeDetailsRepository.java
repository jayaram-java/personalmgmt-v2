package com.company.Personalmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.IncomeDetails;

@Repository
public interface IncomeDetailsRepository extends JpaRepository<IncomeDetails, Long> {

	@Query(nativeQuery = true, value = "CALL expense_trans(:month,:year)")
	List<Object[]> getBalanceSheetDetails(@Param("month") String month, @Param("year") String year);

	@Query(nativeQuery = true, value = "SELECT i.n , i.month , sum(i.amount) FROM income_details AS i WHERE YEAR = :curyear GROUP BY i.n ")
	List<Object[]> getincomedetails(@Param("curyear") int curyear);

}
