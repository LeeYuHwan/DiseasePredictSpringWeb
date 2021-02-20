package kr.or.connect.diseasepredict.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.diseasepredict.dto.diseaseCrawlingContent;

@RestController
@RequestMapping(path="/api/diseaseInfocrawling")
public class showDiseaseInfoCrawlerApiController {
	diseaseCrawlingContent getCrawling(String url, String parse){
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		Elements contents = doc.select(parse);
		diseaseCrawlingContent content = new diseaseCrawlingContent();
		
		for(Element contentTmp : contents) {
			try {
				String text = contentTmp.text();			
				content.setContent(text);
			}catch(Exception e) {
				e.printStackTrace();
			}					
		}
		content.setUrl(url);
		return content;
	}
	
	@GetMapping
	public Map<String, Object> getInfoData(){			
				
		diseaseCrawlingContent parasite_infection = getCrawling("https://terms.naver.com/entry.nhn?docId=3392057&cid=60289&categoryId=60289", ".txt");
		diseaseCrawlingContent entero_infections = getCrawling("https://terms.naver.com/entry.nhn?docId=497778&cid=60408&categoryId=55558", ".txt");
		diseaseCrawlingContent hand_foot_and_mouth_disease = getCrawling("https://terms.naver.com/entry.nhn?docId=2120003&cid=51004&categoryId=51004", ".summary_area");
		diseaseCrawlingContent influenza = getCrawling("https://terms.naver.com/entry.nhn?docId=926584&cid=51007&categoryId=51007", ".txt");
		diseaseCrawlingContent intestinal_infections = getCrawling("https://terms.naver.com/entry.nhn?docId=5550483&cid=51004&categoryId=51004", ".txt");
		diseaseCrawlingContent parasite_infections_abroad = getCrawling("https://terms.naver.com/entry.nhn?docId=3392057&cid=60289&categoryId=60289", ".txt");
		diseaseCrawlingContent respiratory_infections = getCrawling("https://terms.naver.com/entry.nhn?docId=5550552&cid=51004&categoryId=51004", "ol");
		diseaseCrawlingContent sex_infectious_disease = getCrawling("https://terms.naver.com/entry.nhn?docId=927315&cid=51007&categoryId=51007", ".txt");
		diseaseCrawlingContent hepatitis_c = getCrawling("https://terms.naver.com/entry.nhn?docId=926903&cid=51007&categoryId=51007", ".txt");
		
		List<diseaseCrawlingContent> parasite_infectionContent = new ArrayList<>();
		List<diseaseCrawlingContent> entero_infectionsContent = new ArrayList<>();
		List<diseaseCrawlingContent> hand_foot_and_mouth_diseaseContent = new ArrayList<>();
		List<diseaseCrawlingContent> influenzaContent = new ArrayList<>();
		List<diseaseCrawlingContent> intestinal_infectionsContent = new ArrayList<>();
		List<diseaseCrawlingContent> parasite_infections_abroadContent = new ArrayList<>();
		List<diseaseCrawlingContent> respiratory_infectionsContent = new ArrayList<>();
		List<diseaseCrawlingContent> sex_infectious_diseaseContent = new ArrayList<>();
		List<diseaseCrawlingContent> hepatitis_cContent = new ArrayList<>();
		
		parasite_infectionContent.add(parasite_infection);
		entero_infectionsContent.add(entero_infections);
		hand_foot_and_mouth_diseaseContent.add(hand_foot_and_mouth_disease);
		influenzaContent.add(influenza);
		intestinal_infectionsContent.add(intestinal_infections);
		parasite_infections_abroadContent.add(parasite_infections_abroad);
		respiratory_infectionsContent.add(respiratory_infections);
		sex_infectious_diseaseContent.add(sex_infectious_disease);
		hepatitis_cContent.add(hepatitis_c);
		
		Map<String, Object> map = new HashMap<>();		
		map.put("parasite_infectionContent", parasite_infectionContent);
		map.put("entero_infectionsContent", entero_infectionsContent);
		map.put("hand_foot_and_mouth_diseaseContent", hand_foot_and_mouth_diseaseContent);
		map.put("influenzaContent", influenzaContent);
		map.put("intestinal_infectionsContent", intestinal_infectionsContent);
		map.put("parasite_infections_abroadContent", parasite_infections_abroadContent);
		map.put("respiratory_infectionsContent", respiratory_infectionsContent);
		map.put("sex_infectious_diseaseContent", sex_infectious_diseaseContent);
		map.put("hepatitis_cContent", hepatitis_cContent);
		
		return map;
	}
}
