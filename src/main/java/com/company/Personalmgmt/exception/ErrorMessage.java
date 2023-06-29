package com.company.Personalmgmt.exception;

public class ErrorMessage {

	private String status;

	private String statusDesc;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public ErrorMessage(String status, String statusDesc) {
		super();
		this.status = status;
		this.statusDesc = statusDesc;
	}

}
