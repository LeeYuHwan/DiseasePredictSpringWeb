package kr.or.connect.diseasepredict.board.dao;

import static kr.or.connect.diseasepredict.board.dao.MemberSqls.MEMBER_INSERT;
import static kr.or.connect.diseasepredict.board.dao.MemberSqls.MEMBER_INSERT_AUTH;
import static kr.or.connect.diseasepredict.board.dao.MemberSqls.READ;
import static kr.or.connect.diseasepredict.board.dao.MemberSqls.READ_USERID;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.diseasepredict.board.dto.MemberAuthVO;
import kr.or.connect.diseasepredict.board.dto.MemberVO;

@Repository
public class MemberDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<MemberVO> infoRowMapper = BeanPropertyRowMapper.newInstance(MemberVO.class);
	
	public MemberDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}	
	public List<MemberVO> read(int userid){	
		Map<String, ?> params = Collections.singletonMap("userid", userid);
		return jdbc.query(READ, params, infoRowMapper);		
	}
	public List<MemberVO> readUserId(int userid){	
		Map<String, ?> params = Collections.singletonMap("userid", userid);
		return jdbc.query(READ_USERID, params, infoRowMapper);		
	}
	public void addMember(MemberVO memberVO) {
		Map<String, Object> params = new HashMap<>();
		params.put("userid", memberVO.getUserid());
		params.put("userpw", memberVO.getUserpw());
		params.put("username", memberVO.getUserName());
	
		jdbc.update(MEMBER_INSERT, params);
		
	}
	public void addMemberAuth(MemberAuthVO memberAuthVO) {
		Map<String, Object> params = new HashMap<>();
		params.put("userid", memberAuthVO.getUserid());
		
		jdbc.update(MEMBER_INSERT_AUTH, params);		
	}
}
