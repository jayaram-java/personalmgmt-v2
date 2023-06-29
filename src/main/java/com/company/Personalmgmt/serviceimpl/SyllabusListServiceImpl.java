package com.company.Personalmgmt.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Personalmgmt.dto.SyllabusListDetailDto;
import com.company.Personalmgmt.model.Syllabus;
import com.company.Personalmgmt.repository.SyllabusRepository;
import com.company.Personalmgmt.service.SyllabusListService;

@Service
public class SyllabusListServiceImpl implements SyllabusListService {
	
	@Autowired
	SyllabusRepository syllabusRepository;

	public List<SyllabusListDetailDto> getSyllabusList() {

		List<SyllabusListDetailDto> syllabusListDetailDtos = new ArrayList<SyllabusListDetailDto>();

		List<Syllabus> syllabusl = syllabusRepository.findAll();

		for (Syllabus syllabus : syllabusl) {

			SyllabusListDetailDto SyllabusListDetailDto = new SyllabusListDetailDto();
			BeanUtils.copyProperties(syllabus, SyllabusListDetailDto);
			syllabusListDetailDtos.add(SyllabusListDetailDto);
		}

		return syllabusListDetailDtos;
	}

}
