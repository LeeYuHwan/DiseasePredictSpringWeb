package kr.or.connect.diseasepredict.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import kr.or.connect.diseasepredict.dto.covidCityRank;
import kr.or.connect.diseasepredict.dto.covidUpdateInfo;
import kr.or.connect.diseasepredict.dto.covidUpdateInfosJapan;
import kr.or.connect.diseasepredict.service.predictService;

@Controller
public class coronaUpdateController {
	@Autowired
	predictService covidService;
	
	@PostMapping(path="/covid_update_korea")
	public String writeKorea(HttpServletRequest request) {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
	    converters.add(new FormHttpMessageConverter());
	    converters.add(new StringHttpMessageConverter());
	 
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.setMessageConverters(converters);
	 
	    // REST API 호출
	    String result = "";
	    result = restTemplate.getForObject("http://127.0.0.1:5000/seir", String.class);
	    System.out.println("------------------ 호출 결과 ------------------");
	    System.out.println(result);

	    if(result != "") {
	    	covidUpdateInfo covidUpdateInfos = new covidUpdateInfo();
	    	String data[] = result.split(" ");
	    	covidUpdateInfos.setCheckingCounter(Integer.parseInt(data[0]));
	    	covidUpdateInfos.setTotalCase(Integer.parseInt(data[1]));
	    	covidUpdateInfos.setTotalRecovered(Integer.parseInt(data[2]));
	    	covidUpdateInfos.setTotalDeath(Integer.parseInt(data[3]));
	    	Long num = covidService.covidUpdateInfoInsert(covidUpdateInfos);
	    	System.out.println("지금까지 " + num + "개의 날짜와 확진자 정보 업데이트 되었습니다.");
	    	
	    	covidCityRank covidCityRanks = new covidCityRank();
	    	covidCityRanks.setN1(data[4]);
	    	covidCityRanks.setN2(data[5]);
	    	covidCityRanks.setN3(data[6]);
	    	covidCityRanks.setN4(data[7]);
	    	covidCityRanks.setN5(data[8]);
	    	Long num2 = covidService.covidCityRankInsert(covidCityRanks);
	    	System.out.println("지금까지 " + num2 + "개의 도시 순위 정보 업데이트 되었습니다.");
	    }
	    
		return "covid19.html";
	}
	
	@PostMapping(path="/covid_update_japan")
	public String writeJapan(HttpServletRequest request) {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
	    converters.add(new FormHttpMessageConverter());
	    converters.add(new StringHttpMessageConverter());
	 
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.setMessageConverters(converters);
	 
	    // REST API 호출
	    String result = "";
	    result = restTemplate.getForObject("http://127.0.0.1:5000/sir_japan", String.class);
	    System.out.println("------------------ 호출 결과 ------------------");
	    System.out.println(result);

	    if(result != "") {
	    	covidUpdateInfosJapan covidUpdateInfos = new covidUpdateInfosJapan();
	    	String data[] = result.split(" ");
	    	covidUpdateInfos.setTotalCase(Integer.parseInt(data[0]));
	    	covidUpdateInfos.setTotalRecovered(Integer.parseInt(data[1]));
	    	covidUpdateInfos.setTotalDeath(Integer.parseInt(data[2]));
	    	Long num = covidService.covidUpdateInfosJapanInsert(covidUpdateInfos);
	    	System.out.println("지금까지 " + num + "개의 날짜와 확진자 정보 업데이트 되었습니다.");
	    }
	    
		return "covid19.html";
	}
}
