package kr.or.connect.diseasepredict.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;
 
/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
 
    /* NaverLoginBO */
    private NaverController naverLoginBO;
    private String apiResult = null;
    
    @Autowired
    private void setNaverLoginBO(NaverController naverLoginBO) {
        this.naverLoginBO = naverLoginBO;
    }
 
    //로그인 첫 화면 요청 메소드
    @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
    public String login(Model model, HttpSession session) {
        
        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
        
        //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
        //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
        System.out.println("네이버:" + naverAuthUrl);
        
        //네이버 
        model.addAttribute("url", naverAuthUrl);
 
        /* 생성한 인증 URL을 View로 전달 */
        return "login.jsp";
    }
 
    //네이버 로그인 성공시 callback호출 메소드
    @RequestMapping(value = "/call_back", method = { RequestMethod.GET, RequestMethod.POST })
    public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
            throws IOException {
        System.out.println("여기는 call_back");
        OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
        //로그인 사용자 정보를 읽어온다.
        apiResult = naverLoginBO.getUserProfile(oauthToken);
        //System.out.println(apiResult);
        
        JSONParser jp = new JSONParser();     
        JSONObject jres = new JSONObject();
        JSONObject jobj = new JSONObject();
        try {
        	jres = (JSONObject)jp.parse(apiResult);
        	jobj = (JSONObject)jres.get("response");
		} catch (ParseException e) {
			e.printStackTrace();
		}
        System.out.println(jobj.get("name"));
        System.out.println(jobj.get("email"));
        session.setAttribute("name", (String)jobj.get("name"));
        session.setAttribute("email", (String)jobj.get("email"));
        
        return "redirect:index";
    }
    
  //로그아웃
    @RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
    public String logout(HttpSession session)throws IOException {
	    System.out.println("여기는 logout");
	    session.invalidate();
	    return "redirect:index";
    }
}

