package com.company.Personalmgmt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "file_mgmt")
public class FileMgmtDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@XmlAttribute(name = "id")
	private Long id;

	@Column(name = "file_name")
	@XmlElement(name = "file_name")
	private String fileName;

	@Column(name = "upload_dir")
	@XmlElement(name = "upload_dir")
	private String uploadDirection;

	@ManyToOne
	@JoinColumn(name = "file_category_id")
	private FileCategory fileCategory;

	@Column(name = "remarks")
	@XmlElement(name = "remarks")
	private String remarks;

	@Column(name = "document_format")
	@XmlElement(name = "document_format")
	private String documentFormat;

	@Column(name = "base64_format")
	@XmlElement(name = "base64_format")
	private String base64Format;

	@Column(name = "name_from_file")
	@XmlElement(name = "name_from_file")
	private String fileNameFromUploadfile;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUploadDirection() {
		return uploadDirection;
	}

	public void setUploadDirection(String uploadDirection) {
		this.uploadDirection = uploadDirection;
	}

	public FileCategory getFileCategory() {
		return fileCategory;
	}

	public void setFileCategory(FileCategory fileCategory) {
		this.fileCategory = fileCategory;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDocumentFormat() {
		return documentFormat;
	}

	public void setDocumentFormat(String documentFormat) {
		this.documentFormat = documentFormat;
	}

	public String getBase64Format() {
		return base64Format;
	}

	public void setBase64Format(String base64Format) {
		this.base64Format = base64Format;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getFileNameFromUploadfile() {
		return fileNameFromUploadfile;
	}

	public void setFileNameFromUploadfile(String fileNameFromUploadfile) {
		this.fileNameFromUploadfile = fileNameFromUploadfile;
	}

}
