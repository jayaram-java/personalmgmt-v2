package com.company.Personalmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.dto.KeyNotesDto;
import com.company.Personalmgmt.exception.InvalidDataException;
import com.company.Personalmgmt.service.KeyNotesService;

@Controller
@RequestMapping("/keynotes")
public class KeyNotesController {

	@Autowired
	KeyNotesService keyNotesService;

	@RequestMapping(value = "/saveDetails", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public boolean saveKeyNotes(@RequestBody KeyNotesDto keyNotesDto) {

		boolean response = keyNotesService.saveKeyNotesDetails(keyNotesDto);

		return response;
	}

	@RequestMapping(value = "/getAllDetails", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<KeyNotesDto> getAllKeyNotesDetails() {

		List<KeyNotesDto> keyNotesDetailDtos = keyNotesService.getAllKeyNotesDetails();

		return keyNotesDetailDtos;
	}

	@RequestMapping(value = "/getDetailsFromId", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public KeyNotesDto getKeyNotesDetailsFromId(@RequestParam Long id) {

		KeyNotesDto keyNotesDto = keyNotesService.getKeyNotesDetailsFromId(id);

		return keyNotesDto;
	}

	@RequestMapping(value = "/getKeyNotesdetails", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<KeyNotesDto> getRelevantKeyNotesDetails(@RequestParam String name) {

		//System.out.println("Name " + name);
	
		if(name == null || name == ""){
			throw new InvalidDataException("invalid data","exception");
		}

		List<KeyNotesDto> keyNotesDtos = keyNotesService.getRelevantKeyNotesDetails(name);

		return keyNotesDtos;
	}

	@RequestMapping(value = "/keynotescategory", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<KeyNotesDto> getAllKeynotescategory() {

		List<KeyNotesDto> keyNotescategoryDtos = keyNotesService.getAllKeyNotesCategory();

		return keyNotescategoryDtos;

	}

}
