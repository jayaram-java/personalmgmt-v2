package com.company.Personalmgmt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.Personalmgmt.dto.MoveForwardDto;
import com.company.Personalmgmt.dto.ResponseDto;
import com.company.Personalmgmt.exception.InvalidDataException;
import com.company.Personalmgmt.repository.ExpenseCategoryRepository;

@Controller
public class TestController {

	@Autowired
	ExpenseCategoryRepository expenseCategoryRepository;

	@Autowired
	ResponseDto responseDto;

	@RequestMapping(value = "/getTestDetails")
	@ResponseBody
	public List<MoveForwardDto> getMoveForwardDetails(@RequestParam String category) {

		System.out.println(category);

		if (category == null || category == "") {

			System.out.println(category);

			throw new InvalidDataException(responseDto.getStatusFalse(), responseDto.getStatusDesc());

		}

		List<MoveForwardDto> moveForwarddtos = new ArrayList<MoveForwardDto>();

		return moveForwarddtos;
	}

	@RequestMapping(value = "/testingv1")
	@ResponseBody
	public ResponseEntity<?> testing() {

		Map<String, Object> ob = new HashMap<String, Object>();

		ob.put("status", true);
		ob.put("StatusDesc", "Success");

		ResponseEntity<?> response = null;

		response = ResponseEntity.ok(ob);

		return response;
	}

}
