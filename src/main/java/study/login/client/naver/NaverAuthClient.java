package study.login.client.naver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import study.login.dto.naver.NaverOAuthToken;
import study.login.dto.naver.NaverOAuthToken.NaverOAuthTokenDTO;

@FeignClient(name = "naverAuthClient", url = "https://nid.naver.com/oauth2.0")
public interface NaverAuthClient {

	@GetMapping("/token")
	NaverOAuthTokenDTO getNaverAccessToken(
			@RequestParam("grant_type") String grantType,
			@RequestParam("client_id") String clientId,
			@RequestParam("client_secret") String clientSecret,
			@RequestParam String code,
			@RequestParam String state
	);


}
