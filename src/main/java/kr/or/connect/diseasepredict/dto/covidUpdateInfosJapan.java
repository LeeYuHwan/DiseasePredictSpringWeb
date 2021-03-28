package kr.or.connect.diseasepredict.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class covidUpdateInfosJapan {
	private int id;
	private int totalCase;
	private int totalRecovered;
	private int totalDeath;	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTotalCase() {
		return totalCase;
	}
	public void setTotalCase(int totalCase) {
		this.totalCase = totalCase;
	}
	public int getTotalRecovered() {
		return totalRecovered;
	}
	public void setTotalRecovered(int totalRecovered) {
		this.totalRecovered = totalRecovered;
	}
	public int getTotalDeath() {
		return totalDeath;
	}
	public void setTotalDeath(int totalDeath) {
		this.totalDeath = totalDeath;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
	private Date updatedate;
}
