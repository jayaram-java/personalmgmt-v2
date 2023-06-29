package com.company.Personalmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.Syllabus;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, Long> {

}
