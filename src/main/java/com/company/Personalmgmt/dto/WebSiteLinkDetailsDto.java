package com.company.Personalmgmt.dto;

import java.util.Date;

public class WebSiteLinkDetailsDto {

	private Long id;

	private String name;

	private String description;

	private String webSiteLink;

	private String createdDate;

	private int serialId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebSiteLink() {
		return webSiteLink;
	}

	public void setWebSiteLink(String webSiteLink) {
		this.webSiteLink = webSiteLink;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getSerialId() {
		return serialId;
	}

	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}

}
