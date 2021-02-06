package kr.or.connect.diseasepredict.dao;

import static kr.or.connect.diseasepredict.dao.sqls.SEX_INFECTIOUS_DISEASE_ALL;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.diseasepredict.dto.showCharts;

@Repository
public class showChartsInfectiousDiseaseDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<showCharts> rowMapper = BeanPropertyRowMapper.newInstance(showCharts.class);
	
	public showChartsInfectiousDiseaseDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("sex_infectious_disease")
				.usingGeneratedKeyColumns("id");
	}
	
	public List<showCharts> selectAll(){			
		return jdbc.query(SEX_INFECTIOUS_DISEASE_ALL, Collections.emptyMap(), rowMapper);		
	}
	
}
