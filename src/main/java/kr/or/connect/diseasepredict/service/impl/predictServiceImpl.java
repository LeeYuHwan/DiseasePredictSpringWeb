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
	showChartsInfectiousDiseaseDao showChartsDao;
	
	@Override
	public List<showCharts> getSexInfectiousDiseaseAll() {
		List<showCharts> list = showChartsDao.sexInfectiousDiseaseAll();
		return list;
	}

	@Override
	public List<showCharts> getEnteroInfectionsAll() {
		List<showCharts> list = showChartsDao.enteroInfectionsAll();
		return list;
	}

	@Override
	public List<showCharts> getHFMDiseaseAll() {
		List<showCharts> list = showChartsDao.HFMDiseaseAll();
		return list;
	}

	@Override
	public List<showCharts> getHepatitisCAll() {
		List<showCharts> list = showChartsDao.hepatitisCAll();
		return list;
	}

	@Override
	public List<showCharts> getInfluenzaAll() {
		List<showCharts> list = showChartsDao.influenzaAll();
		return list;
	}

	@Override
	public List<showCharts> getIntestinalInfectionsAll() {
		List<showCharts> list = showChartsDao.IntestinalInfectionsAll();
		return list;
	}

	@Override
	public List<showCharts> getParasiteInfectionAll() {
		List<showCharts> list = showChartsDao.parasiteInfectionAll();
		return list;
	}

	@Override
	public List<showCharts> getParasiteInfectionsAbroadAll() {
		List<showCharts> list = showChartsDao.parasiteInfectionsAbroadAll();
		return list;
	}

	@Override
	public List<showCharts> getRespiratoryInfectionsAll() {
		List<showCharts> list = showChartsDao.respiratoryInfectionsAll();
		return list;
	}

}
