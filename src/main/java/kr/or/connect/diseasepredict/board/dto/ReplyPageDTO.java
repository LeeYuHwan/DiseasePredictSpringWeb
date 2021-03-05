package kr.or.connect.diseasepredict.board.dto;

import java.util.List;

public class ReplyPageDTO {
	private int replyCnt;
	private List<ReplyVO> list;
	public ReplyPageDTO(int countByBno, List<ReplyVO> listWithPaging) {
		this.replyCnt = countByBno;
		this.list = listWithPaging;
	}
	public int getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}
	public List<ReplyVO> getList() {
		return list;
	}
	public void setList(List<ReplyVO> list) {
		this.list = list;
	}
}
