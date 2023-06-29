package com.company.Personalmgmt.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Personalmgmt.dto.PriorityDto;
import com.company.Personalmgmt.model.Priority;
import com.company.Personalmgmt.repository.PriorityRepository;
import com.company.Personalmgmt.service.PriorityService;

@Service
public class PriorityServiceImpl implements PriorityService {
	
	@Autowired
	PriorityRepository priorityRepository;

	public boolean savePriorityDetails(PriorityDto priorityDto) {

		if (priorityDto.getId() == null) {

			Priority priority = new Priority();

			priority.setName(priorityDto.getName());
			priority.setLevel(priorityDto.getLevel());
			priority.setDate(priorityDto.getDate());

			priorityRepository.save(priority);

		} else {

			Priority priority = priorityRepository.findById((long) priorityDto.getId());

			priority.setName(priorityDto.getName());
			priority.setLevel(priorityDto.getLevel());
			priority.setDate(priorityDto.getDate());

			priorityRepository.save(priority);

		}

		return true;
	}

	public List<PriorityDto> getAllPriorityList() {

		List<PriorityDto> priorityDtos = new ArrayList<PriorityDto>();

		List<Priority> priorities = priorityRepository.findAll();

		int n = 1;

		for (Priority priority : priorities) {

			PriorityDto priorityDto = new PriorityDto();

			BeanUtils.copyProperties(priority, priorityDto);

			priorityDto.setSerialId(n);

			priorityDtos.add(priorityDto);

			n++;

		}

		return priorityDtos;
	}

	public PriorityDto getpriorityById(Long id) {

		PriorityDto priorityDto = new PriorityDto();

		Priority priority = priorityRepository.findById((long) id);

		BeanUtils.copyProperties(priority, priorityDto);

		return priorityDto;
	}

}
