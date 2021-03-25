package kr.or.connect.diseasepredict.dto;

public class diseaseContent {
	private int id;
	private String name;
	private String content;
	private String aboutUrl;
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAboutUrl() {
		return aboutUrl;
	}
	public void setAboutUrl(String aboutUrl) {
		this.aboutUrl = aboutUrl;
	}
}
