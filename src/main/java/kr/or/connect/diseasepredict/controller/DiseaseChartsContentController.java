package kr.or.connect.diseasepredict.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.diseasepredict.dto.diseaseContent;
import kr.or.connect.diseasepredict.service.predictService;

@RestController
@RequestMapping(path="/api/charts_content")
public class DiseaseChartsContentController {
	@Autowired
	predictService covidService;
	
	@GetMapping
	public Map<String, Object> covidUpdateInfo(){	
		List<diseaseContent> contents = covidService.contentAll();
				
		Map<String, Object> map = new HashMap<>();
		
		map.put("contents", contents);	
		
		return map;
	}

}
