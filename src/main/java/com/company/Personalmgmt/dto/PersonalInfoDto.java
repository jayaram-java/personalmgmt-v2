package com.company.Personalmgmt.dto;

public class PersonalInfoDto {

	private Long id;

	private String name;

	private String dob;

	private String aadhaarNo;

	private String panNo;

	private String phoneNumber;

	private String age;

	private String createdLDate;

	private String modifiedLDate;

	private String companyName;

	private String webLink;

	private String designation;
	
	private String bloodGroup;
	
	private String passportNumber;
	
	private String uan;

	@Override
	public String toString() {
		return "PersonalInfoDto [id=" + id + ", name=" + name + ", dob=" + dob + ", aadhaarNo=" + aadhaarNo + ", panNo="
				+ panNo + ", phoneNumber=" + phoneNumber + ", age=" + age + ", createdLDate=" + createdLDate
				+ ", modifiedLDate=" + modifiedLDate + ", companyName=" + companyName + ", webLink=" + webLink
				+ ", designation=" + designation + ", bloodGroup=" + bloodGroup + ", passportNumber=" + passportNumber
				+ ", uan=" + uan + "]";
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getUan() {
		return uan;
	}

	public void setUan(String uan) {
		this.uan = uan;
	}

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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAadhaarNo() {
		return aadhaarNo;
	}

	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCreatedLDate() {
		return createdLDate;
	}

	public void setCreatedLDate(String createdLDate) {
		this.createdLDate = createdLDate;
	}

	public String getModifiedLDate() {
		return modifiedLDate;
	}

	public void setModifiedLDate(String modifiedLDate) {
		this.modifiedLDate = modifiedLDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getWebLink() {
		return webLink;
	}

	public void setWebLink(String webLink) {
		this.webLink = webLink;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}
