package kr.or.connect;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import kr.or.connect.diseasepredict.dto.diseaseCrawlingContent;

public class jsoupTest {
	public static void main(String[] args) {
		Document doc = null;
		try {
			doc = Jsoup.connect("https://terms.naver.com/entry.nhn?docId=3392057&cid=60289&categoryId=60289").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		Elements contents = doc.select(".txt");
		diseaseCrawlingContent content = new diseaseCrawlingContent();
		
		for(Element contentTmp : contents) {
			try {
				String num = contentTmp.text();			
				System.out.println(num);

			}catch(Exception e) {
				
			}					
		}	
	}
}
