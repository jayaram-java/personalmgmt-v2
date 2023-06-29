package com.company.Personalmgmt.exception;

public class InvalidDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

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

	public InvalidDataException(String status, String statusDesc) {
		super();
		this.status = status;
		this.statusDesc = statusDesc;
	}

}
