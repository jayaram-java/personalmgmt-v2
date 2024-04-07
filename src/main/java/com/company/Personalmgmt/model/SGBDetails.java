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
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the details of Sovereign Gold Bonds (SGBs) for personal
 * management.
 * 
 * @author Jayaram
 */
@Entity
@Table(name = "sgb_details")
public class SGBDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "bond_id")
	private String bondId;

	@Column(name = "purchase_date")
	private Date purchaseDate;

	@Column(name = "redemption_date")
	private Date redemptionDate;

	@Column(name = "purchased_grams")
	private Integer purchasedGrams;

	@Column(name = "per_gram_amount")
	private Double perGramAmount;

	@Column(name = "purchase_amount")
	private Double purchaseAmount;

	@Column(name = "tenure_years")
	private Integer tenureYears;

	@Column(name = "redemption_years")
	private Integer redemptionYears;

	@ManyToOne
	@JoinColumn(name = "month_master_id")
	private MonthMaster monthMaster;

	@ManyToOne
	@JoinColumn(name = "year_master_id")
	private YearMaster yearMaster;

	@Column(name = "creation_date")
	private Date creationDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modification_date")
	private Date modificationDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "nominee_name")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String nomineeName;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "is_active")
	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBondId() {
		return bondId;
	}

	public void setBondId(String bondId) {
		this.bondId = bondId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getRedemptionDate() {
		return redemptionDate;
	}

	public void setRedemptionDate(Date redemptionDate) {
		this.redemptionDate = redemptionDate;
	}

	public Integer getPurchasedGrams() {
		return purchasedGrams;
	}

	public void setPurchasedGrams(Integer purchasedGrams) {
		this.purchasedGrams = purchasedGrams;
	}

	public Double getPerGramAmount() {
		return perGramAmount;
	}

	public void setPerGramAmount(Double perGramAmount) {
		this.perGramAmount = perGramAmount;
	}

	public Double getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(Double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public Integer getTenureYears() {
		return tenureYears;
	}

	public void setTenureYears(Integer tenureYears) {
		this.tenureYears = tenureYears;
	}

	public Integer getRedemptionYears() {
		return redemptionYears;
	}

	public void setRedemptionYears(Integer redemptionYears) {
		this.redemptionYears = redemptionYears;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
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

}
