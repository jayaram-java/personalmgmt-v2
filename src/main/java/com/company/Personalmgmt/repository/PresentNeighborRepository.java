package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.PresentNeighbor;

@Repository
public interface PresentNeighborRepository extends JpaRepository<PresentNeighbor,Long>{

}
