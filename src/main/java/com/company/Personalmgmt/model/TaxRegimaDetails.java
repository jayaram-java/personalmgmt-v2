/**
 * 
 */
package com.company.Personalmgmt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is used for
 *
 * @author Jayaram
 */

@Entity
@Table(name = "master_tax_regima")
public class TaxRegimaDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "type")
	private String type;

	@Column(name = "income_tax_rate")
	private String incomeTaxRate;

	@Column(name = "tax_rate")
	private Integer taxRate;

	@Column(name = "income_slab")
	private Integer incomeSlab;

	@Column(name = "is_active")
	private String isActive;

	@Column(name = "remark")
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIncomeTaxRate() {
		return incomeTaxRate;
	}

	public void setIncomeTaxRate(String incomeTaxRate) {
		this.incomeTaxRate = incomeTaxRate;
	}

	public Integer getIncomeSlab() {
		return incomeSlab;
	}

	public void setIncomeSlab(Integer incomeSlab) {
		this.incomeSlab = incomeSlab;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Integer getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Integer taxRate) {
		this.taxRate = taxRate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
