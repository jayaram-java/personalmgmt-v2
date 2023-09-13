/**
 * 
 */
package com.company.Personalmgmt.dto;

/**
 * This class is used for
 *
 * @author Jayaram
 */

public class BillerDetails {

	private String payeeId;
	private String payeeName;
	private String nickName;
	private String accountNumber;
	private String status;
	private String addressLine1;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private String businessDaysToDeliver;
	private String ebillSupport;
	private String eBillStatus;
	private String nextAvailablePaymentDeliveryDate;
	private String paymentMethod;
	private String isManuallyAdded;

	public String getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBusinessDaysToDeliver() {
		return businessDaysToDeliver;
	}

	public void setBusinessDaysToDeliver(String businessDaysToDeliver) {
		this.businessDaysToDeliver = businessDaysToDeliver;
	}

	public String getEbillSupport() {
		return ebillSupport;
	}

	public void setEbillSupport(String ebillSupport) {
		this.ebillSupport = ebillSupport;
	}

	public String geteBillStatus() {
		return eBillStatus;
	}

	public void seteBillStatus(String eBillStatus) {
		this.eBillStatus = eBillStatus;
	}

	public String getNextAvailablePaymentDeliveryDate() {
		return nextAvailablePaymentDeliveryDate;
	}

	public void setNextAvailablePaymentDeliveryDate(String nextAvailablePaymentDeliveryDate) {
		this.nextAvailablePaymentDeliveryDate = nextAvailablePaymentDeliveryDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getIsManuallyAdded() {
		return isManuallyAdded;
	}

	public void setIsManuallyAdded(String isManuallyAdded) {
		this.isManuallyAdded = isManuallyAdded;
	}

}
