package com.company.Personalmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.Syllabus;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, Long> {

	List<Syllabus> findByLevelIn(List<String> levels);

}
