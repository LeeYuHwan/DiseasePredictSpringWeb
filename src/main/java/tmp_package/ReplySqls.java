package tmp_package;

public class ReplySqls {
	public static final String READ = "select * from tbl_reply where rno = :rno";
	public static final String DELETE = "delete from tbl_reply where rno = :rno";
	public static final String UPDATE = "update tbl_reply set reply = :reply, updatedate = sysdate where rno = :rno";
	public static final String GET_LIST_WITH_PAGING = "select rno, bno, reply, replyer, replyDate, updatedate " + 
			"from (" + 
			"select rownum rn, rno, bno, reply, replyer, replyDate, updatedate " + 
			"from tbl_reply " + 
			"where bno = :bno " + 
			"and rno > 0 " + 
			"and :cri_pageNum * :cri_amount >= rownum " + 
			") " + 
			"where rn > (:cri_pageNum - 1) * :cri_amount";
	public static final String GET_COUNT_BY_BNO = "select count(rno) from tbl_reply where bno = :bno";
}
