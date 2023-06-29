package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.MoveForward;

@Repository
public interface MoveForwardRepository extends JpaRepository<MoveForward, Long> {

}
