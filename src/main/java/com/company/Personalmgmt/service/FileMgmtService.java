package com.company.Personalmgmt.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.company.Personalmgmt.dto.FileMgmtDto;

public interface FileMgmtService {

	String storeFile(MultipartFile file);

	boolean saveFileWithDetails(FileMgmtDto fileMgmtDto, Map<String, MultipartFile> multiparts);

	List<FileMgmtDto> getAllUploadedFiles();
	
	FileMgmtDto getUploadedFileFromId(Long id);

}
