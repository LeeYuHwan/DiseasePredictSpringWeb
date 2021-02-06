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
		List<showCharts> items = chartService.getShowCharts();
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("items", items);	
		
		return map;
	}
}
