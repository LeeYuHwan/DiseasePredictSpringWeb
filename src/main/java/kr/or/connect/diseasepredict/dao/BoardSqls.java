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
}
