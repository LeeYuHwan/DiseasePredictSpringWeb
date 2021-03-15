package kr.or.connect.diseasepredict.board.dao;

public class MemberSqls {
	public static final String READ =  "SELECT mem.userid, userpw, username, enabled, regdate, updatedate, auth " + 
			"FROM " + 
			"tbl_member mem LEFT OUTER JOIN tbl_member_auth auth on mem.userid = auth.userid " + 
			"WHERE mem.userid = :userid";
	public static final String READ_USERID = "select count(*) from tbl_member where userid = :userid";
	public static final String MEMBER_INSERT = "insert into tbl_member (userid, userpw, username, regdate, updatedate, enabled) " + 
			"values (:userid, :userpw, :username, sysdate, sysdate, 1)";
	public static final String MEMBER_INSERT_AUTH = "insert into tbl_member_auth (userid, auth) " + 
			"values (:userid, 'ROLE_MEMBER')";
}
