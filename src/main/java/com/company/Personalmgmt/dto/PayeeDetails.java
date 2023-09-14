/**
 * 
 */
package com.company.Personalmgmt.dto;

import java.util.List;

/**
 * This class is used for
 *
 * @author Jayaram
 */

public class PayeeDetails {

	private String requestId;
	private boolean backwardFlag;
	private boolean forwardFlag;
	private String responseStatus;
	private String timestamp;
	private int httpStatusCode;
	private List<Payee> billers;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public boolean isBackwardFlag() {
		return backwardFlag;
	}

	public void setBackwardFlag(boolean backwardFlag) {
		this.backwardFlag = backwardFlag;
	}

	public boolean isForwardFlag() {
		return forwardFlag;
	}

	public void setForwardFlag(boolean forwardFlag) {
		this.forwardFlag = forwardFlag;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public List<Payee> getBillers() {
		return billers;
	}

	public void setBillers(List<Payee> billers) {
		this.billers = billers;
	}

}
