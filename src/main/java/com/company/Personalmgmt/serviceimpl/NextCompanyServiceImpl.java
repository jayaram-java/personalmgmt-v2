package com.company.Personalmgmt.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Personalmgmt.model.NextCompany;
import com.company.Personalmgmt.repository.NextCompanyRepository;
import com.company.Personalmgmt.service.NextCompanyService;

@Service
public class NextCompanyServiceImpl implements NextCompanyService {
	
	@Autowired
	private NextCompanyRepository nextCompanyRepository;

	@Override
	public List<NextCompany> getNextCompanyDetails() {

		List<NextCompany> nextCompany = nextCompanyRepository.findAll();

		return nextCompany;
	}

}
