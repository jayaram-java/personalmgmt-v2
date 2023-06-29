package com.company.Personalmgmt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.company.Personalmgmt.dto.CheckListDetailDto;
import com.company.Personalmgmt.dto.FileMgmtDto;
import com.company.Personalmgmt.model.FileMgmtDetails;
import com.company.Personalmgmt.service.FileMgmtService;

@Controller
public class FileMgmtController {
	
	@Autowired
	FileMgmtService fileMgmtService;

	@RequestMapping(value = "/savefiledetails")
	@ResponseBody
	public boolean uploadFile(MultipartHttpServletRequest getUploadedfiles, HttpServletResponse response,
			FileMgmtDto fileMgmtDto) {

		boolean res = false;

		try {

			MultipartFile file = getUploadedfiles.getFile("file");
			Map<String, MultipartFile> multiparts = new HashMap<String, MultipartFile>();
			multiparts.put("file", file);

			if (file == null) {
				System.out.println("level 1");
			} else {

				res = fileMgmtService.saveFileWithDetails(fileMgmtDto, multiparts);
			}

			System.out.println(fileMgmtDto.getFileName());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}
	
	@RequestMapping(value = "/getalluploadedfiles")
	@ResponseBody
	public List<FileMgmtDto> getAllUploadedFiles(){
		
		List<FileMgmtDto> fileMgmtDto = fileMgmtService.getAllUploadedFiles();
				
		return fileMgmtDto;
		
	}
	
	@RequestMapping(value = "/getuploadedfilefromid")
	@ResponseBody
	public FileMgmtDto getUplodedFileFromId(@RequestParam Long id) {

		FileMgmtDto fileMgmtDto = fileMgmtService.getUploadedFileFromId(id);

		return fileMgmtDto;

	}
	

}
