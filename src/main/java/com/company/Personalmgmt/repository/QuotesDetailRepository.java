package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.QuotesDetail;

@Repository
public interface QuotesDetailRepository extends JpaRepository<QuotesDetail, Long> {

}
