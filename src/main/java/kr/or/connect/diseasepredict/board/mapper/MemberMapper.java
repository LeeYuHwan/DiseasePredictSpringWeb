package kr.or.connect.diseasepredict.board.mapper;

import kr.or.connect.diseasepredict.board.dto.MemberVO;

public interface MemberMapper {
	public MemberVO read(String userid);
	public boolean read_userid(String userid);
	public void insert(MemberVO member);
}
