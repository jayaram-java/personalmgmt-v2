package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.SecondaryUserDetails;

@Repository
public interface SecondaryUserDetailsRepository extends JpaRepository<SecondaryUserDetails, Long> {

	SecondaryUserDetails findByEmailAddress(String email);

}
