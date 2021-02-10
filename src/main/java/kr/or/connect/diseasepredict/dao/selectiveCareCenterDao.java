package kr.or.connect.diseasepredict.dao;

import static kr.or.connect.diseasepredict.dao.sqls.SELECTIVE_CARE_CENTER_ALL;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.diseasepredict.dto.selectiveCareCenter;

@Repository
public class selectiveCareCenterDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<selectiveCareCenter> rowMapper = BeanPropertyRowMapper.newInstance(selectiveCareCenter.class);
	
	public selectiveCareCenterDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		/*this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("sex_infectious_disease")
				.usingGeneratedKeyColumns("id");*/
	}
	
	public List<selectiveCareCenter> selectiveCareCenterAll(){			
		return jdbc.query(SELECTIVE_CARE_CENTER_ALL, Collections.emptyMap(), rowMapper);		
	}
}
