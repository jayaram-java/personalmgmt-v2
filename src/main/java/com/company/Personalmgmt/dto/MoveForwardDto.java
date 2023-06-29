package com.company.Personalmgmt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MoveForwardDto {

	private long id;
	private String target;
	private String endDate;
	private String status;
	private String remarks;
	private String dependsOnPerson;
	private String personDob;

	private String remainingDays;

	private String personAge;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDependsOnPerson() {
		return dependsOnPerson;
	}

	public void setDependsOnPerson(String dependsOnPerson) {
		this.dependsOnPerson = dependsOnPerson;
	}

	public String getPersonDob() {
		return personDob;
	}

	public void setPersonDob(String personDob) {
		this.personDob = personDob;
	}

	public String getRemainingDays() {
		return remainingDays;
	}

	public void setRemainingDays(String remainingDays) {
		this.remainingDays = remainingDays;
	}

	public String getPersonAge() {
		return personAge;
	}

	public void setPersonAge(String personAge) {
		this.personAge = personAge;
	}

}
