package com.company.Personalmgmt.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.Personalmgmt.model.StatusMaster;
import com.company.Personalmgmt.repository.StatusMasterRepository;

@RestController
public class MasterController {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(MasterController.class);


	@Autowired
	StatusMasterRepository statusMasterRepository;

	@RequestMapping(value = "/fetchStatus", method = { RequestMethod.POST, RequestMethod.GET })
	public List<StatusMaster> getAllStatusDetails() {

		List<StatusMaster> statusMaster = statusMasterRepository.findAll();
		
		log.info(statusMaster.toString());

		return statusMaster;
	}

}
