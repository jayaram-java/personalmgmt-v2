package com.company.Personalmgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.WebSiteLinkDetails;

@Repository
public interface WebSiteLinkDetailsRepository extends JpaRepository<WebSiteLinkDetails, Long> {

	Optional<WebSiteLinkDetails> findByOrderByIdDesc();

}
