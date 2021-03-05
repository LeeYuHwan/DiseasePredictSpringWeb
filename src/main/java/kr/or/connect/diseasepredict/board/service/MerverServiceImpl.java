package kr.or.connect.diseasepredict.board.service;

import kr.or.connect.diseasepredict.board.dto.MemberVO;
import kr.or.connect.diseasepredict.board.mapper.MemberMapper;

public class MerverServiceImpl implements MemberService{
	private MemberMapper memberMapper;
	
	@Override
	public boolean read_userid(String userid) {
		return memberMapper.read_userid(userid);
	}

	@Override
	public void insert(MemberVO member) {
		memberMapper.insert(member);
	}

}
