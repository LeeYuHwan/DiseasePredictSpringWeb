package kr.or.connect.diseasepredict.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class NaverController{
	private final static String CLIENT_ID = "zK_x_xvgrPtCQjCWeLMY";
	private final static String CLIENT_SECRET = "lzJZKPmFG7";
	private final static String REDIRECT_URI = "http://localhost:8080/connect/call_back";
	private final static String SESSION_STATE = "oauth_state";
    private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
	
    /* URL 생성 */
	public String getAuthorizationUrl(HttpSession session) {
		// TODO Auto-generated method stub
		String state = generateRandomString();
		setSession(session, state);
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URI)
				.state(state)
				.build(NaverLoginApi.instance());
		return oauthService.getAuthorizationUrl();
	}
	
	public static OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException{
		// TODO Auto-generated method stub
		String sessionState = getSession(session);
		if (StringUtils.pathEquals(sessionState, state)) {
			OAuth20Service oauthService = new ServiceBuilder()
                    .apiKey(CLIENT_ID)
                    .apiSecret(CLIENT_SECRET)
                    .callback(REDIRECT_URI)
                    .state(state)
                    .build(NaverLoginApi.instance());
 
            OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
            return accessToken;
		}
		return null;
	}
	
	public static String getUserProfile(OAuth2AccessToken oauthToken) throws IOException {
		// TODO Auto-generated method stub
		OAuth20Service oauthService =new ServiceBuilder()
                .apiKey(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .callback(REDIRECT_URI).build(NaverLoginApi.instance());
 
            OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
        oauthService.signRequest(oauthToken, request);
        Response response = request.send();
        return response.getBody();
	}
	
	/* 세션 유효성 검증 난수 생성 */
	private String generateRandomString() {
		return UUID.randomUUID().toString();
	}
	
	/* http session 데이터 저장 */
	private void setSession(HttpSession session, String state) {
		session.setAttribute(SESSION_STATE, state);
	}
	
	/* http session 데이터 가져오기 */
	private static String getSession(HttpSession session) {
		return (String) session.getAttribute(SESSION_STATE);
	}
	
}
