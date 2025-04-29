package study.login.client.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import study.login.dto.kakao.KaKaoOAuthToken;

@FeignClient(name = "kakaoAuthClient", url = "https://kauth.kakao.com")
public interface KaKaoAuthClient {

	@PostMapping("/oauth/token")
	KaKaoOAuthToken.KaKaoOAuthTokenDTO getKaKaoAccessToken(
			@RequestHeader("Content-Type") String contentType,
			@RequestParam String grant_type,
			@RequestParam String redirectUri,
			@RequestParam String client_id,
			@RequestParam(defaultValue = "authorization_code") String code);
}