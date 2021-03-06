package kr.or.connect.diseasepredict.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.diseasepredict.dto.covidCityRank;
import kr.or.connect.diseasepredict.dto.covidUpdateInfo;
import kr.or.connect.diseasepredict.dto.covidUpdateInfosJapan;
import kr.or.connect.diseasepredict.dto.covidUpdateInfosUS;
import kr.or.connect.diseasepredict.service.predictService;

@RestController
@RequestMapping(path="/api/covid_update_Info")
public class covidUpdateInfoApiController {
	@Autowired
	predictService covidService;
	
	@GetMapping
	public Map<String, Object> covidUpdateInfo(){	
		List<covidUpdateInfo> covidUpdateInfos = covidService.covidUpdateInfoAll();
				
		Map<String, Object> map = new HashMap<>();
		
		map.put("covidUpdateInfos", covidUpdateInfos);	
		
		return map;
	}
	
	@GetMapping("/america")
	public Map<String, Object> covidUpdateInfoAmerica(){	
		List<covidUpdateInfosUS> covidUpdateInfos = covidService.covidUpdateInfoUSAll();
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("covidUpdateInfos", covidUpdateInfos);	
		
		return map;
	}
	
	@GetMapping("/japan")
	public Map<String, Object> covidUpdateInfoJapan(){	
		List<covidUpdateInfosJapan> covidUpdateInfos = covidService.covidUpdateInfoJapanAll();
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("covidUpdateInfos", covidUpdateInfos);	
		
		return map;
	}
	
	@GetMapping("/city_rank")
	public Map<String, Object> covidCityRank(){	
		List<covidCityRank> covidCityRanks = covidService.covidCityRankAll();
				
		Map<String, Object> map = new HashMap<>();
		
		map.put("covidCityRanks", covidCityRanks);	
		
		return map;
	}
}
