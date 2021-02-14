package kr.or.connect.diseasepredict.dao;

import static kr.or.connect.diseasepredict.dao.sqls.COVID_UPDATE_INFO_ALL;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.diseasepredict.dto.covidUpdateInfo;

@Repository
public class covidUpdateInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<covidUpdateInfo> rowMapper = BeanPropertyRowMapper.newInstance(covidUpdateInfo.class);
	
	public covidUpdateInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("covid_update_info")
				.usingGeneratedKeyColumns("id");
	}
	
	public List<covidUpdateInfo> covidUpdateInfoAll(){			
		return jdbc.query(COVID_UPDATE_INFO_ALL, Collections.emptyMap(), rowMapper);		
	}
	
	public Long covidUpdateInfoInsert(covidUpdateInfo covidUpdateInfos) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(covidUpdateInfos);
		return insertAction.executeAndReturnKey(params).longValue();
	}
}
