package kr.or.connect.diseasepredict.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class coronaUpdateController {
	@PostMapping(path="/covid_update")
	public String write(HttpServletRequest request) {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
	    converters.add(new FormHttpMessageConverter());
	    converters.add(new StringHttpMessageConverter());
	 
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.setMessageConverters(converters);
	 
	    // REST API 호출
	    String result = restTemplate.getForObject("http://127.0.0.1:5000/seir", String.class);
	    System.out.println("------------------ 호출 결과 ------------------");
	    System.out.println(result);
		
		return "covid19.html";
	}
}
