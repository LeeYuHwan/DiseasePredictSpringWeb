package kr.or.connect.diseasepredict.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class covidUpdateInfo {
	private int id;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
	private Date updatedate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
}
