package kr.or.connect.diseasepredict.dao;

import static kr.or.connect.diseasepredict.dao.sqls.ENTERO_INFECTIONS_ALL;
import static kr.or.connect.diseasepredict.dao.sqls.HEPATITIS_C_ALL;
import static kr.or.connect.diseasepredict.dao.sqls.HFM_DISEASE_ALL;
import static kr.or.connect.diseasepredict.dao.sqls.INFLUENZA_ALL;
import static kr.or.connect.diseasepredict.dao.sqls.INTESTIAL_INFECTIONS_ALL;
import static kr.or.connect.diseasepredict.dao.sqls.PARASITE_INFECTION_ABROAD_ALL;
import static kr.or.connect.diseasepredict.dao.sqls.PARASITE_INFECTION_ALL;
import static kr.or.connect.diseasepredict.dao.sqls.RESPIRATORY_INFECTIONS_ALL;
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
		/*this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("sex_infectious_disease")
				.usingGeneratedKeyColumns("id");*/
	}
	
	public List<showCharts> sexInfectiousDiseaseAll(){			
		return jdbc.query(SEX_INFECTIOUS_DISEASE_ALL, Collections.emptyMap(), rowMapper);		
	}
	
	public List<showCharts> enteroInfectionsAll(){			
		return jdbc.query(ENTERO_INFECTIONS_ALL, Collections.emptyMap(), rowMapper);		
	}
	
	public List<showCharts> HFMDiseaseAll(){			
		return jdbc.query(HFM_DISEASE_ALL, Collections.emptyMap(), rowMapper);		
	}
	
	public List<showCharts> hepatitisCAll(){			
		return jdbc.query(HEPATITIS_C_ALL, Collections.emptyMap(), rowMapper);		
	}
	
	public List<showCharts> influenzaAll(){			
		return jdbc.query(INFLUENZA_ALL, Collections.emptyMap(), rowMapper);		
	}
	
	public List<showCharts> IntestinalInfectionsAll(){			
		return jdbc.query(INTESTIAL_INFECTIONS_ALL, Collections.emptyMap(), rowMapper);		
	}
	
	public List<showCharts> parasiteInfectionAll(){			
		return jdbc.query(PARASITE_INFECTION_ALL, Collections.emptyMap(), rowMapper);		
	}
	
	public List<showCharts> parasiteInfectionsAbroadAll(){			
		return jdbc.query(PARASITE_INFECTION_ABROAD_ALL, Collections.emptyMap(), rowMapper);		
	}
	
	public List<showCharts> respiratoryInfectionsAll(){			
		return jdbc.query(RESPIRATORY_INFECTIONS_ALL, Collections.emptyMap(), rowMapper);		
	}	
}
