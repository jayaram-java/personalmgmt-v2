package com.company.Personalmgmt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


@Entity
@Table(name = "startup_details")
public class StartupDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@XmlAttribute(name = "id")
	private Long id;

	@Column(name = "startup_name")
	@XmlElement(name = "startup_name")
	private String name;
	
	@Column(name = "website_link")
	@XmlElement(name = "website_link")
	private String webSiteLink;
	
	@Column(name = "video_link")
	@XmlElement(name = "video_link")
	private String videoLink;
	
	@Column(name = "created_date")
	@XmlElement(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_date")
	@XmlElement(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "description")
	@XmlElement(name = "description")
	private String description;

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

	public String getWebSiteLink() {
		return webSiteLink;
	}

	public void setWebSiteLink(String webSiteLink) {
		this.webSiteLink = webSiteLink;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
