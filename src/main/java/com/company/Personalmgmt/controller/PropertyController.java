package com.company.Personalmgmt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.dto.PropertyDto;
import com.company.Personalmgmt.dto.WebSiteLinkDetailsDto;
import com.company.Personalmgmt.model.InterLinkSoftware;
import com.company.Personalmgmt.model.WebSiteLinkDetails;
import com.company.Personalmgmt.repository.InterLinkSoftwareRepository;
import com.company.Personalmgmt.repository.WebSiteLinkDetailsRepository;
import com.company.Personalmgmt.service.PropertyService;
import com.company.Personalmgmt.service.WebSiteLinkService;

@Controller
@RequestMapping(value = "/property")
public class PropertyController {

	@Autowired
	PropertyService propertyService;
	
	@Autowired
	WebSiteLinkDetailsRepository webSiteLinkDetailsRepository;
	
	@Autowired
	InterLinkSoftwareRepository interLinkSoftwareRepository;
	
	@Autowired
	WebSiteLinkService webSiteLinkService;

	@RequestMapping(value = "/fetchalldetails")
	@ResponseBody
	public List<PropertyDto> fetchAllPropertyDetails() {

		List<PropertyDto> propertyDtos = propertyService.fetchAllPropertyDetails();

		return propertyDtos;

	}

	@RequestMapping(value = "/savepropertydetails")
	@ResponseBody
	public boolean savePropertyDetails(@RequestBody PropertyDto propertyDto) {

		boolean response = propertyService.savePropertyDetails(propertyDto);

		return response;
	}
	
	
	@RequestMapping(value = "/getpropertydetailsfromid")
	@ResponseBody
	public PropertyDto getPropertyDetailsFromId(@RequestParam Long id) {

		PropertyDto propertyDto = propertyService.getPropertyDetailsFromId(id);

		return propertyDto;

	}
	
	
	@RequestMapping(value = "/getallwebsitelinks")
	@ResponseBody
	public List<WebSiteLinkDetailsDto> getAllWebSiteLinks() {

		List<WebSiteLinkDetailsDto> ob = webSiteLinkService.getAllWebSiteLinks();

		
		return ob;

	}
	
	@RequestMapping(value = "/getallinterlinks")
	@ResponseBody
	public List<InterLinkSoftware> getAllInterLinks() {

		List<InterLinkSoftware> interLinkSoftware = interLinkSoftwareRepository.findAll();

		return interLinkSoftware;

	}

}
