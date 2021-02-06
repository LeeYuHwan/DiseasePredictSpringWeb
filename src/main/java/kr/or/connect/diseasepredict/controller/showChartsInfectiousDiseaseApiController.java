package kr.or.connect.diseasepredict.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.diseasepredict.dto.showCharts;
import kr.or.connect.diseasepredict.service.predictService;

@RestController
@RequestMapping(path="/api/showCharts")
public class showChartsInfectiousDiseaseApiController {
	@Autowired
	predictService chartService;
	
	@GetMapping
	public Map<String, Object> list(){	
		List<showCharts> EnteroInfections = chartService.getEnteroInfectionsAll();
		List<showCharts> HepatitisC = chartService.getHepatitisCAll();
		List<showCharts> HFMDisease = chartService.getHFMDiseaseAll();
		List<showCharts> Influenza = chartService.getInfluenzaAll();
		List<showCharts> IntestinalInfections = chartService.getIntestinalInfectionsAll();
		List<showCharts> ParasiteInfection = chartService.getParasiteInfectionAll();
		List<showCharts> ParasiteInfectionsAbroad = chartService.getParasiteInfectionsAbroadAll();
		List<showCharts> RespiratoryInfections = chartService.getRespiratoryInfectionsAll();
		List<showCharts> SexInfectiousDisease = chartService.getSexInfectiousDiseaseAll();
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("EnteroInfections", EnteroInfections);	
		map.put("HepatitisC", HepatitisC);
		map.put("HFMDisease", HFMDisease);
		map.put("Influenza", Influenza);
		map.put("IntestinalInfections", IntestinalInfections);
		map.put("ParasiteInfection", ParasiteInfection);
		map.put("ParasiteInfectionsAbroad", ParasiteInfectionsAbroad);
		map.put("RespiratoryInfections", RespiratoryInfections);
		map.put("SexInfectiousDisease", SexInfectiousDisease);
		
		return map;
	}
}
