/**
 * 
 */
package com.company.Personalmgmt.dto;

import com.company.Personalmgmt.searialization.BillerResponseDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * This class is used for
 *
 * @author Jayaram
 */

@JsonDeserialize(using = BillerResponseDeserializer.class)
public class BillerResponse {

	private String requestId;
	private String responseStatus;
	private String timestamp;
	private Biller[] billers;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
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

	public Biller[] getBillers() {
		return billers;
	}

	public void setBillers(Biller[] billers) {
		this.billers = billers;
	}

}
