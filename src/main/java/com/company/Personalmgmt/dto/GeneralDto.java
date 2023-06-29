package com.company.Personalmgmt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralDto {

	private long id;

	private String month;

	private String mode;

	private double amount;

	private double indAmount;

	private String type;

	private String time;

	private int year;

	private String timeDiff;

	private double incomeAmt;

	private double expenseAmt;

	private double balanceAmt;

	public double getIndAmount() {
		return indAmount;
	}

	public void setIndAmount(double indAmount) {
		this.indAmount = indAmount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimeDiff() {
		return timeDiff;
	}

	public void setTimeDiff(String timeDiff) {
		this.timeDiff = timeDiff;
	}

	public double getIncomeAmt() {
		return incomeAmt;
	}

	public void setIncomeAmt(double incomeAmt) {
		this.incomeAmt = incomeAmt;
	}

	public double getExpenseAmt() {
		return expenseAmt;
	}

	public void setExpenseAmt(double expenseAmt) {
		this.expenseAmt = expenseAmt;
	}

	public double getBalanceAmt() {
		return balanceAmt;
	}

	public void setBalanceAmt(double balanceAmt) {
		this.balanceAmt = balanceAmt;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
