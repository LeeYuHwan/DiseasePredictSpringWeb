package kr.or.connect.diseasepredict.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.diseasepredict.dto.selectiveCareCenter;
import kr.or.connect.diseasepredict.service.predictService;

@RestController
@RequestMapping(path="/api/selective_care_center")
public class selectiveCareCenterApiController {
	//selectiveCareCenter
	@Autowired
	predictService careCenterService;
	
	@GetMapping
	public Map<String, Object> careCentersList(){	
		List<selectiveCareCenter> careCenters = careCenterService.getSelectiveCareCenterAll();
				
		Map<String, Object> map = new HashMap<>();
		
		map.put("careCenters", careCenters);	
		
		return map;
	}
}
