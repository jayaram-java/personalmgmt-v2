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
@Table(name = "income_details")
public class IncomeDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@XmlAttribute(name = "id")
	private Long id;

	@Column(name = "amount")
	@XmlElement(name = "amount")
	private double amount;

	@Column(name = "given_date")
	@XmlElement(name = "given_date")
	private Date givenDate;

	@Column(name = "company")
	@XmlElement(name = "company")
	private String company;

	@Column(name = "month")
	@XmlElement(name = "month")
	private String month;

	@Column(name = "year")
	@XmlElement(name = "year")
	private String year;

	@Column(name = "remarks")
	@XmlElement(name = "remarks")
	private String remarks;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "profession_tax")
	private Double professionTax;

	@Column(name = "provident_fund")
	private Double providentFund;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getProfessionTax() {
		return professionTax;
	}

	public void setProfessionTax(Double professionTax) {
		this.professionTax = professionTax;
	}

	public Double getProvidentFund() {
		return providentFund;
	}

	public void setProvidentFund(Double providentFund) {
		this.providentFund = providentFund;
	}

}
