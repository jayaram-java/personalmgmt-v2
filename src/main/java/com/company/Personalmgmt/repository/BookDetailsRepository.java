package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.BookDetails;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetails, Long> {

}
