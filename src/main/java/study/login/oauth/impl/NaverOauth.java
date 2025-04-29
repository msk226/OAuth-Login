package study.login.oauth.impl;

import static study.login.dto.naver.NaverOAuthToken.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import study.login.client.naver.NaverApiClient;
import study.login.client.naver.NaverAuthClient;
import study.login.dto.kakao.KaKaoOAuthToken;
import study.login.dto.kakao.KaKaoUser;
import study.login.dto.naver.NaverUser;
import study.login.oauth.SocialOauth;

@Slf4j
@Component
@RequiredArgsConstructor
public class NaverOauth implements SocialOauth {

	@Value("${spring.OAuth2.naver.client-id}")
	private String NAVER_CLIENT_ID;

	@Value("${spring.OAuth2.naver.client-secret}")
	private String NAVER_CLIENT_SECRET;

	@Value("${spring.OAuth2.naver.callback-login-url}")
	private String NAVER_CALLBACK_LOGIN_URL;


	private static final String GRANT_TYPE = "authorization_code";
	private static final String NAVER_SNS_URL = "https://nid.naver.com/oauth2.0/authorize";
	private static final String STATE = "STATE_STRING";

	private final NaverAuthClient naverAuthClient;
	private final NaverApiClient naverApiClient;


	@Override
	public String getOauthRedirectURL() {

		Map<String, Object> params = new HashMap<>();
		params.put("client_id", NAVER_CLIENT_ID);
		params.put("redirect_uri", NAVER_CALLBACK_LOGIN_URL);
		params.put("response_type", "code");
		params.put("state", STATE);

		String parameterString = params.entrySet().stream()
				.map(x -> x.getKey() + "=" + x.getValue())
				.collect(Collectors.joining("&"));

		String redirectURL = NAVER_SNS_URL + "?" + parameterString;
		log.info("redirectURL = {}", redirectURL);
		return redirectURL;
	}

	public NaverOAuthTokenDTO requestAccessToken(String code) {
		NaverOAuthTokenDTO naverOAuthTokenDTO =  naverAuthClient.getNaverAccessToken(
				 GRANT_TYPE, NAVER_CLIENT_ID, NAVER_CLIENT_SECRET, code, STATE);
		log.info(naverOAuthTokenDTO.toString());
		return naverOAuthTokenDTO;
	}


	public NaverUser requestUserInfo(NaverOAuthTokenDTO naverOAuthTokenDTO) {
		NaverUser naverUser = naverApiClient.getNaverUserInfo(
				getAccessToken(naverOAuthTokenDTO));
		log.info(naverUser.toString());
		return naverUser;
	}

	private static String getAccessToken (NaverOAuthTokenDTO naverOAuthTokenDTO) {
		return "Bearer " + naverOAuthTokenDTO.getAccess_token();
	}
}
