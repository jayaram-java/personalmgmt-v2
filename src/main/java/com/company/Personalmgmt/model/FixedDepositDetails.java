/**
 * 
 */
package com.company.Personalmgmt.model;

/**
* This class is used for 
*
* @author Jayaram
*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fixed_deposits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "depositId")
public class FixedDepositDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "deposit_id", unique = true, nullable = false)
	private Long depositId;

	@Column(name = "deposit_account_no", length = 50)
	private String depositAccountNo;

	@Column(name = "bank_name", length = 100)
	private String bankName;

	@Column(name = "deposit_date")
	private LocalDateTime depositDate;

	@Column(name = "principal_amount", precision = 15, scale = 2)
	private BigDecimal principalAmount;

	@Column(name = "fixed_interest_rate", precision = 15, scale = 2)
	private BigDecimal fixedInterestRate;

	@Column(name = "interest_amount", precision = 15, scale = 2)
	private BigDecimal interestAmount;

	@Column(name = "maturity_amount", precision = 15, scale = 2)
	private BigDecimal maturityAmount;

	@Column(name = "tax_amount", precision = 15, scale = 2)
	private BigDecimal taxAmount;

	@Column(name = "maturity_date")
	private LocalDateTime maturityDate;

	@Column(name = "tenure_days")
	private Integer tenureDays;

	@ManyToOne
	@JoinColumn(name = "month_id")
	private MonthMaster monthMaster;

	@ManyToOne
	@JoinColumn(name = "year_id")
	private YearMaster yearMaster;

	@Column(name = "created_at", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Column(name = "created_by", length = 100)
	private String createdBy;

	@Column(name = "updated_by", length = 100)
	private String updatedBy;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "is_active")
	private Boolean isActive = true;

	@Column(name = "nominee_name", length = 100)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String nomineeName;

	@Column(name = "remark", length = 100)
	private String remark;

	@Column(name = "is_renewed")
	private Boolean isRenewed = false;

	@ManyToOne
	@JoinColumn(name = "renewed_from_id")
	private FixedDepositHistory renewedFrom;

	@Override
	public String toString() {
		return "FixedDepositDetails [depositId=" + depositId + ", depositAccountNo=" + depositAccountNo + ", bankName="
				+ bankName + ", depositDate=" + depositDate + ", principalAmount=" + principalAmount
				+ ", fixedInterestRate=" + fixedInterestRate + ", interestAmount=" + interestAmount
				+ ", maturityAmount=" + maturityAmount + ", taxAmount=" + taxAmount + ", maturityDate=" + maturityDate
				+ ", tenureMonths=" + tenureDays + ", monthMaster=" + monthMaster + ", yearMaster=" + yearMaster
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", createdBy=" + createdBy + ", updatedBy="
				+ updatedBy + ", user=" + user + ", isActive=" + isActive + ", nomineeName=" + nomineeName + ", remark="
				+ remark + ", isRenewed=" + isRenewed + ", renewedFrom=" + renewedFrom + "]";
	}

	public Long getDepositId() {
		return depositId;
	}

	public void setDepositId(Long depositId) {
		this.depositId = depositId;
	}

	public String getDepositAccountNo() {
		return depositAccountNo;
	}

	public void setDepositAccountNo(String depositAccountNo) {
		this.depositAccountNo = depositAccountNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public LocalDateTime getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(LocalDateTime depositDate) {
		this.depositDate = depositDate;
	}

	public BigDecimal getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(BigDecimal principalAmount) {
		this.principalAmount = principalAmount;
	}

	public BigDecimal getFixedInterestRate() {
		return fixedInterestRate;
	}

	public void setFixedInterestRate(BigDecimal fixedInterestRate) {
		this.fixedInterestRate = fixedInterestRate;
	}

	public BigDecimal getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(BigDecimal interestAmount) {
		this.interestAmount = interestAmount;
	}

	public BigDecimal getMaturityAmount() {
		return maturityAmount;
	}

	public void setMaturityAmount(BigDecimal maturityAmount) {
		this.maturityAmount = maturityAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public LocalDateTime getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDateTime maturityDate) {
		this.maturityDate = maturityDate;
	}

	public MonthMaster getMonthMaster() {
		return monthMaster;
	}

	public void setMonthMaster(MonthMaster monthMaster) {
		this.monthMaster = monthMaster;
	}

	public YearMaster getYearMaster() {
		return yearMaster;
	}

	public void setYearMaster(YearMaster yearMaster) {
		this.yearMaster = yearMaster;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getIsRenewed() {
		return isRenewed;
	}

	public void setIsRenewed(Boolean isRenewed) {
		this.isRenewed = isRenewed;
	}

	public FixedDepositHistory getRenewedFrom() {
		return renewedFrom;
	}

	public void setRenewedFrom(FixedDepositHistory renewedFrom) {
		this.renewedFrom = renewedFrom;
	}

	public Integer getTenureDays() {
		return tenureDays;
	}

	public void setTenureDays(Integer tenureDays) {
		this.tenureDays = tenureDays;
	}

	// Getters and Setters (or Lombok @Getter, @Setter annotations if preferred)

}
