package tmp_package;

import static tmp_package.ReplySqls.DELETE;
import static tmp_package.ReplySqls.GET_COUNT_BY_BNO;
import static tmp_package.ReplySqls.GET_LIST_WITH_PAGING;
import static tmp_package.ReplySqls.READ;
import static tmp_package.ReplySqls.UPDATE;

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

import kr.or.connect.diseasepredict.board.dto.Criteria;
import kr.or.connect.diseasepredict.board.dto.ReplyVO;

@Repository
public class ReplyDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ReplyVO> infoRowMapper = BeanPropertyRowMapper.newInstance(ReplyVO.class);
	
	public ReplyDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("tbl_reply")
				.usingGeneratedKeyColumns("rno");
	}	
	public List<ReplyVO> read(int rno){	
		Map<String, ?> params = Collections.singletonMap("rno", rno);
		return jdbc.query(READ, params, infoRowMapper);		
	}
	public void delete(int rno){
		Map<String, ?> params = Collections.singletonMap("rno", rno);
		jdbc.update(DELETE, params);
		
		System.out.println(rno + "의 자료를 삭제 하였습니다.");
	}
	public void update(ReplyVO replyVO, int rno){	
		Map<String, Object> params = new HashMap<>();
		params.put("reply", replyVO.getReply());
		params.put("rno", rno);
		jdbc.update(UPDATE, params);
		
		System.out.println(rno + "의 자료를 업데이트 하였습니다.");
	}
	public List<ReplyVO> getListWithPaging(ReplyVO replyVO, Criteria criteria,int bno){	
		Map<String, Object> params = new HashMap<>();
		params.put("bno", bno);
		params.put("cri_pageNum", criteria.getPageNum());
		params.put("cri_amount", criteria.getAmount());
		params.put("cri_pageNum", criteria.getPageNum());
		params.put("cri_amount", criteria.getAmount());
		
		return jdbc.query(GET_LIST_WITH_PAGING, params, infoRowMapper);
	}
	public List<ReplyVO> getCountByBno(int bno){
		Map<String, ?> params = Collections.singletonMap("bno", bno);
		return jdbc.query(GET_COUNT_BY_BNO, params, infoRowMapper);		
	}
	public long insert(ReplyVO replyVO){			
		SqlParameterSource params = new BeanPropertySqlParameterSource(replyVO);
		return insertAction.executeAndReturnKey(params).longValue();		
	}
}
