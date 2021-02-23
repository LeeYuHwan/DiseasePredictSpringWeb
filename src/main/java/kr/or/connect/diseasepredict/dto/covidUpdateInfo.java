package kr.or.connect.diseasepredict.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class covidUpdateInfo {
	private int id;
	private int checkingCounter;
	private int TotalCase;
	private int TotalRecovered;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
	private Date updatedate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCheckingCounter() {
		return checkingCounter;
	}
	public void setCheckingCounter(int checkingCounter) {
		this.checkingCounter = checkingCounter;
	}
	public int getTotalCase() {
		return TotalCase;
	}
	public void setTotalCase(int totalCase) {
		TotalCase = totalCase;
	}
	public int getTotalRecovered() {
		return TotalRecovered;
	}
	public void setTotalRecovered(int totalRecovered) {
		TotalRecovered = totalRecovered;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
}
