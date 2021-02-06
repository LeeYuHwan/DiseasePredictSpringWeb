package kr.or.connect.diseasepredict.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.diseasepredict.dao.showChartsInfectiousDiseaseDao;
import kr.or.connect.diseasepredict.dto.showCharts;
import kr.or.connect.diseasepredict.service.predictService;

@Service
public class predictServiceImpl implements predictService{
	@Autowired
	showChartsInfectiousDiseaseDao sexInfectiousDao;
	
	@Override
	public List<showCharts> getShowCharts() {
		List<showCharts> list = sexInfectiousDao.selectAll();
		return list;
	}

}
