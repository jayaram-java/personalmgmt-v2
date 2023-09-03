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

@Entity
@Table(name = "deposit_details")
public class DepositDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 38)
	private Long id;

	@Column(name = "deposit_acc_no")
	private String depositAccNo;

	@Column(name = "principal_amt")
	private Double principalAmt;

	@Column(name = "fixed_rate_interest")
	private Double fri;

	@Column(name = "deposit_date")
	private String depositDate;

	@Column(name = "maturity_date")
	private String maturityDate;

	@Column(name = "maturity_amt")
	private Double maturityAmt;

	@Column(name = "interest_amt")
	private Double interestAmt;

	@Column(name = "tenure")
	private Integer tenure;

	@ManyToOne
	@JoinColumn(name = "month_id")
	private MonthMaster monthMaster;

	@ManyToOne
	@JoinColumn(name = "year_id")
	private YearMaster yearMaster;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "bank_name")
	private String bankName;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "is_active")
	private String isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepositAccNo() {
		return depositAccNo;
	}

	public void setDepositAccNo(String depositAccNo) {
		this.depositAccNo = depositAccNo;
	}

	public Double getPrincipalAmt() {
		return principalAmt;
	}

	public void setPrincipalAmt(Double principalAmt) {
		this.principalAmt = principalAmt;
	}

	public Double getFri() {
		return fri;
	}

	public void setFri(Double fri) {
		this.fri = fri;
	}

	public String getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(String depositDate) {
		this.depositDate = depositDate;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Double getMaturityAmt() {
		return maturityAmt;
	}

	public void setMaturityAmt(Double maturityAmt) {
		this.maturityAmt = maturityAmt;
	}

	public Double getInterestAmt() {
		return interestAmt;
	}

	public void setInterestAmt(Double interestAmt) {
		this.interestAmt = interestAmt;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public MonthMaster getMonthMaster() {
		return monthMaster;
	}

	public void setMonthMaster(MonthMaster monthMaster) {
		this.monthMaster = monthMaster;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public YearMaster getYearMaster() {
		return yearMaster;
	}

	public void setYearMaster(YearMaster yearMaster) {
		this.yearMaster = yearMaster;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}
