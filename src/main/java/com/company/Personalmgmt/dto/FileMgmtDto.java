package com.company.Personalmgmt.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class FileMgmtDto {

	private long id;

	private long serialId;

	private String fileName;

	private String fileCategory;

	private String remarks;

	private Date createdDate;

	private MultipartFile file;

	private String storeDate;

	private String uploadDirection;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileCategory() {
		return fileCategory;
	}

	public void setFileCategory(String fileCategory) {
		this.fileCategory = fileCategory;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getSerialId() {
		return serialId;
	}

	public void setSerialId(long serialId) {
		this.serialId = serialId;
	}

	public String getStoreDate() {
		return storeDate;
	}

	public void setStoreDate(String storeDate) {
		this.storeDate = storeDate;
	}

	public String getUploadDirection() {
		return uploadDirection;
	}

	public void setUploadDirection(String uploadDirection) {
		this.uploadDirection = uploadDirection;
	}

}
