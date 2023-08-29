/**
 * 
 */
package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.ElectronicItemDetails;

/**
 * @author Jayaram
 * To improve the code standard
 */

@Repository
public interface ElectronicItemDetailsRepository  extends JpaRepository<ElectronicItemDetails, Long> {

}
