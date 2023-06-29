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
@Table(name = "debt_management")
public class DebtManagement implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@XmlAttribute(name = "id")
	private Long id;

	@Column(name = "person_name")
	@XmlElement(name = "person_name")
	private String name;

	@Column(name = "amount")
	@XmlElement(name = "amount")
	private double amount;

	@Column(name = "given_date")
	@XmlElement(name = "given_date")
	private Date givenDate;

	@Column(name = "month")
	@XmlElement(name = "month")
	private String month;

	@Column(name = "year")
	@XmlElement(name = "year")
	private String year;

	@Column(name = "status")
	@XmlElement(name = "status")
	private String status;

	@Column(name = "issued_by")
	@XmlElement(name = "issued_by")
	private String issuedBy;

	@Column(name = "remarks")
	@XmlElement(name = "remarks")
	private String remarks;

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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getGivenDate() {
		return givenDate;
	}

	public void setGivenDate(Date givenDate) {
		this.givenDate = givenDate;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
