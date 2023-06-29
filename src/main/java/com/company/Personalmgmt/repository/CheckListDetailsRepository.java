package com.company.Personalmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.CheckListDetails;

@Repository
public interface CheckListDetailsRepository extends JpaRepository<CheckListDetails, Long> {

	List<CheckListDetails> findByCheckListCategoryIdAndUserId(Long id, long userid);

	List<CheckListDetails> findByCheckListCategoryNameAndUserId(String name, long userid);

	CheckListDetails findById(long id);

	List<CheckListDetails> findByUserId(long id);

}
