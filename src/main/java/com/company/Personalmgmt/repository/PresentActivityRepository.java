package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.PresentActivity;

@Repository
public interface PresentActivityRepository extends JpaRepository<PresentActivity, Long> {

}
