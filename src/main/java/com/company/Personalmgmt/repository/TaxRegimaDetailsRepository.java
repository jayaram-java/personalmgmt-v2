/**
 * 
 */
package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.TaxRegimaDetails;

/**
* This class is used for 
*
* @author Jayaram
*/

@Repository
public interface TaxRegimaDetailsRepository extends JpaRepository<TaxRegimaDetails, Long>{

}
