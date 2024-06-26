package com.company.Personalmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.DepositDetails;

@Repository
public interface DepositDetailsRepository extends JpaRepository<DepositDetails, Long> {

	List<DepositDetails> findByUserIdAndIsActive(long id, String status);

	List<DepositDetails> findByDepositAccNo(String accountNo);
	
    List<DepositDetails> findDistinctByBankNameIsNotNull();
    
    List<DepositDetails> findByYearMasterYearAndBankNameAndIsActive(String year,String bankName,String status);
    
    List<DepositDetails> findByBankNameAndIsActive(String bankName,String status);
	
}
