/**
 * 
 */
package com.company.Personalmgmt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class is used for
 *
 * @author Jayaram
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Biller {

	private Address address;
	private String BusinessDaysToDeliver;
	private String billerId;
	private String billerName;
	private String nickName;
	private String ebillSupport;
	private String accountNumber;
	private String NextAvailablePaymentDeliveryDate;
	private String eBillStatus;
	private String paymentMethod;
	private String status;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getBusinessDaysToDeliver() {
		return BusinessDaysToDeliver;
	}

	public void setBusinessDaysToDeliver(String businessDaysToDeliver) {
		BusinessDaysToDeliver = businessDaysToDeliver;
	}

	public String getBillerId() {
		return billerId;
	}

	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}

	public String getBillerName() {
		return billerName;
	}

	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEbillSupport() {
		return ebillSupport;
	}

	public void setEbillSupport(String ebillSupport) {
		this.ebillSupport = ebillSupport;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getNextAvailablePaymentDeliveryDate() {
		return NextAvailablePaymentDeliveryDate;
	}

	public void setNextAvailablePaymentDeliveryDate(String nextAvailablePaymentDeliveryDate) {
		NextAvailablePaymentDeliveryDate = nextAvailablePaymentDeliveryDate;
	}

	public String geteBillStatus() {
		return eBillStatus;
	}

	public void seteBillStatus(String eBillStatus) {
		this.eBillStatus = eBillStatus;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
