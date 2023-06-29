package com.company.Personalmgmt.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:message.properties")
public class ResponseDto {

	@Value("${statusSuccess}")
	private String statusSuccess;

	@Value("${statusDesc}")
	private String statusDesc;

	@Value("${statusFalse}")
	private String statusFalse;

	public String getStatusSuccess() {
		return statusSuccess;
	}

	public void setStatusSuccess(String statusSuccess) {
		this.statusSuccess = statusSuccess;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getStatusFalse() {
		return statusFalse;
	}

	public void setStatusFalse(String statusFalse) {
		this.statusFalse = statusFalse;
	}

}
