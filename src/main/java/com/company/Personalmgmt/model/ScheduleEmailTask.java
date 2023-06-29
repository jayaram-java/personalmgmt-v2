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
@Table(name = "schedule_email_task")
public class ScheduleEmailTask implements Serializable {
	
	

	public ScheduleEmailTask() {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@XmlAttribute(name = "id")
	private Long id;

	@Column(name = "purpose")
	@XmlElement(name = "purpose")
	private String purpose;

	@Column(name = "month")
	@XmlElement(name = "month")
	private String month;

	@Column(name = "year")
	@XmlElement(name = "year")
	private String year;

	@Column(name = "is_done")
	@XmlElement(name = "is_done")
	private String isDone;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "created_date")
	@XmlElement(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_date")
	@XmlElement(name = "modified_date")
	private Date modifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getIsDone() {
		return isDone;
	}

	public void setIsDone(String isDone) {
		this.isDone = isDone;
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

	public ScheduleEmailTask(String purpose, String month, String year, String isDone, User user,
			Date createdDate) {
		super();
		this.purpose = purpose;
		this.month = month;
		this.year = year;
		this.isDone = isDone;
		this.user = user;
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
