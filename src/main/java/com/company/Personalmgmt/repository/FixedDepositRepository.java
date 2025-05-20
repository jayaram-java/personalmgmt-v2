/**
 * 
 */
package com.company.Personalmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.FixedDepositDetails;

/**
 * This class is used for
 *
 * @author Jayaram
 */

@Repository
public interface FixedDepositRepository extends JpaRepository<FixedDepositDetails, Long> {

	List<FixedDepositDetails> findByUserIdAndIsActive(long id, boolean status);
	
	List<FixedDepositDetails> findByUserIdAndIsActiveAndBankName(long id, boolean status,String bankName);

}
