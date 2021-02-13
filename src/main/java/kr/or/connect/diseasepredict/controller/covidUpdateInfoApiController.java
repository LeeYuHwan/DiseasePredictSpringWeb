package kr.or.connect.diseasepredict.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.diseasepredict.dto.covidUpdateInfo;
import kr.or.connect.diseasepredict.service.predictService;

@RestController
@RequestMapping(path="/api/covid_update_Info")
public class covidUpdateInfoApiController {
	@Autowired
	predictService covidService;
	
	@GetMapping
	public Map<String, Object> careCentersList(){	
		List<covidUpdateInfo> covidUpdateInfos = covidService.covidUpdateInfoAll();
				
		Map<String, Object> map = new HashMap<>();
		
		map.put("covidUpdateInfos", covidUpdateInfos);	
		
		return map;
	}
}
