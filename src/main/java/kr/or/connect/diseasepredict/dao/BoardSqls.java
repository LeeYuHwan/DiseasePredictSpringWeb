package kr.or.connect.diseasepredict.dao;

public class BoardSqls {
	public static final String GET_LIST = "select * from tbl_board where bno > 0";	
	public static final String READ = "select * from tbl_board where bno = :bno";
	public static final String DELETE = "delete from tbl_board where bno = :bno";
	public static final String UPDATE = "update tbl_board " + 
			"set title= :title, " + 
			"content= :content, " + 
			"writer = :writer, " + 
			"updateDate = sysdate " + 
			"where bno = :bno";
	public static final String GET_TOTAL_COUNT = "select count(*) from tbl_board where bno > 0";
	public static final String GET_TITLE = "SELECT * FROM tbl_board WHERE title = :search";
	public static final String GET_CONTENT = "SELECT * FROM tbl_board WHERE content LIKE :search";
	public static final String GET_WRITER = "SELECT * FROM tbl_board WHERE writer = :search";
	public static final String GET_CONTENT_TITLE = "SELECT * FROM tbl_board WHERE content LIKE :content OR title = :search";
	public static final String GET_TITLE_WRITER = "SELECT * FROM tbl_board WHERE title = :search OR writer = :search";
	public static final String GET_TITLE_CONTENT_WRITER = "SELECT * FROM tbl_board WHERE title = :search OR content LIKE :content OR writer = :search";
}
