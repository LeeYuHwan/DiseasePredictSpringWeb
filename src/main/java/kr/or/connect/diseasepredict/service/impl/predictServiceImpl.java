package kr.or.connect.diseasepredict.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.diseasepredict.dao.covidUpdateInfoDao;
import kr.or.connect.diseasepredict.dao.selectiveCareCenterDao;
import kr.or.connect.diseasepredict.dao.showChartsInfectiousDiseaseDao;
import kr.or.connect.diseasepredict.dto.covidCityRank;
import kr.or.connect.diseasepredict.dto.covidUpdateInfo;
import kr.or.connect.diseasepredict.dto.diseaseContent;
import kr.or.connect.diseasepredict.dto.selectiveCareCenter;
import kr.or.connect.diseasepredict.dto.showCharts;
import kr.or.connect.diseasepredict.service.predictService;

@Service
public class predictServiceImpl implements predictService{
	@Autowired
	showChartsInfectiousDiseaseDao showChartsDao;
	@Autowired
	selectiveCareCenterDao CareCenterDao;
	@Autowired
	covidUpdateInfoDao covidUpdateDao;
	
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
	
	@Override
	public List<diseaseContent> contentAll() {
		List<diseaseContent> list = showChartsDao.contentAll();
		return list;
	}

	@Override
	public List<selectiveCareCenter> getSelectiveCareCenterAll() {
		List<selectiveCareCenter> list = CareCenterDao.selectiveCareCenterAll();
		return list;
	}

	@Override
	public List<covidUpdateInfo> covidUpdateInfoAll() {		
		List<covidUpdateInfo> list = covidUpdateDao.covidUpdateInfoAll();
		return list;
	}

	@Override
	public List<covidCityRank> covidCityRankAll() {
		List<covidCityRank> list = covidUpdateDao.covidCityRankAll();
		return list;
	}
	
	@Override
	@Transactional(readOnly = false)
	public Long covidUpdateInfoInsert(covidUpdateInfo covidUpdateInfos) {
		covidUpdateInfos.setUpdatedate(new Date());
		Long num = covidUpdateDao.covidUpdateInfoInsert(covidUpdateInfos);
		return num;
	}

	@Override
	@Transactional(readOnly = false)
	public Long covidCityRankInsert(covidCityRank covidCityRanks) {
		Long num = covidUpdateDao.covidCityRankInsert(covidCityRanks);
		return num;
	}		
}
