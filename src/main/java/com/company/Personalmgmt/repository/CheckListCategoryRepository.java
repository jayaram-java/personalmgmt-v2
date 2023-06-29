package com.company.Personalmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.Personalmgmt.model.CheckListCategory;

@Repository
public interface CheckListCategoryRepository extends JpaRepository<CheckListCategory, Long> {

	List<CheckListCategory> findByUserId(long userid);

	CheckListCategory findByName(String name);

	List<CheckListCategory> findByStatus(String status);

	List<CheckListCategory> findByStatusAndUserId(String status, long userid);

	CheckListCategory findByStatusAndUserIdAndPrimaryFlag(String status, long userid, String primaryFlag);

}
