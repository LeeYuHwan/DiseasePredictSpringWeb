package kr.or.connect.diseasepredict.dao;

import static kr.or.connect.diseasepredict.dao.BoardSqls.DELETE;
import static kr.or.connect.diseasepredict.dao.BoardSqls.GET_LIST;
import static kr.or.connect.diseasepredict.dao.BoardSqls.GET_TOTAL_COUNT;
import static kr.or.connect.diseasepredict.dao.BoardSqls.READ;
import static kr.or.connect.diseasepredict.dao.BoardSqls.UPDATE;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.diseasepredict.board.dto.BoardVO;

@Repository
public class BoardDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<BoardVO> infoRowMapper = BeanPropertyRowMapper.newInstance(BoardVO.class);
	
	public BoardDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("tbl_board")
				.usingGeneratedKeyColumns("bno");
	}
	
	public List<BoardVO> getList(){			
		return jdbc.query(GET_LIST, Collections.emptyMap(), infoRowMapper);		
	}
	public BoardVO read(Long bno){	
		Map<String, ?> params = Collections.singletonMap("bno", bno);
		return jdbc.queryForObject(READ, params, infoRowMapper);		
	}
	public void delete(Long bno){
		Map<String, ?> params = Collections.singletonMap("bno", bno);
		jdbc.update(DELETE, params);
		
		System.out.println(bno + "의 자료를 삭제 하였습니다.");
	}
	public void update(BoardVO boardVO, Long bno){	
		Map<String, Object> params = new HashMap<>();
		params.put("title", boardVO.getTitle());
		params.put("content", boardVO.getContent());
		params.put("writer", boardVO.getWriter());
		params.put("bno", bno);		
		jdbc.update(UPDATE, params);
		
		System.out.println(bno + "의 자료를 업데이트 하였습니다.");
	}
	public Long getTotalCount(){			
		return jdbc.queryForObject(GET_TOTAL_COUNT, Collections.emptyMap(), Long.class);		
	}
	public Long insert(BoardVO boradVO){			
		SqlParameterSource params = new BeanPropertySqlParameterSource(boradVO);
		return insertAction.executeAndReturnKey(params).longValue();		
	}
}
