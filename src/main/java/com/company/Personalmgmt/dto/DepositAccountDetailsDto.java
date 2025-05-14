/**
 * 
 */
package com.company.Personalmgmt.dto;

/**
 * This class is used for
 *
 * @author Jayaram
 */

public class DepositAccountDetailsDto {

	private Long id;
	private String accountNumber;
	private Double principalAmount;
	private Double interestRate;
	private String openingDate;
	private String maturityDate;
	private Double maturityAmount;
	private Double interestAmount;
	private Double taxAmount;
	private Integer tenureInMonths;
	private String bankName;
	private String remark;
	private String nomineeName;
	
	

	@Override
	public String toString() {
		return "DepositAccountDetailsDto [id=" + id + ", accountNumber=" + accountNumber + ", principalAmount="
				+ principalAmount + ", interestRate=" + interestRate + ", openingDate=" + openingDate
				+ ", maturityDate=" + maturityDate + ", maturityAmount=" + maturityAmount + ", interestAmount="
				+ interestAmount + ", taxAmount=" + taxAmount + ", tenureInMonths=" + tenureInMonths + ", bankName="
				+ bankName + ", remark=" + remark + ", nomineeName=" + nomineeName + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(Double principalAmount) {
		this.principalAmount = principalAmount;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public String getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Double getMaturityAmount() {
		return maturityAmount;
	}

	public void setMaturityAmount(Double maturityAmount) {
		this.maturityAmount = maturityAmount;
	}

	public Double getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(Double interestAmount) {
		this.interestAmount = interestAmount;
	}

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Integer getTenureInMonths() {
		return tenureInMonths;
	}

	public void setTenureInMonths(Integer tenureInMonths) {
		this.tenureInMonths = tenureInMonths;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

}
