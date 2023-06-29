package com.company.Personalmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.model.PresentNeighbor;
import com.company.Personalmgmt.service.NeighbourService;

@Controller
public class NeighbourController {

	@Autowired
	NeighbourService neighbourService;

	@RequestMapping(value = "/getallneighbourdetails")
	@ResponseBody
	public List<PresentNeighbor> getAllNeighbourDetails() {

		List<PresentNeighbor> presentNeighbors = neighbourService.getAllNeighbourDetails();

		return presentNeighbors;

	}

}
