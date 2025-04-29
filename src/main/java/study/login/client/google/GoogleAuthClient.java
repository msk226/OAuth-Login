package study.login.client.google;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import study.login.dto.google.GoogleOAuthToken;

@FeignClient(name = "googleAuthClient", url = "https://oauth2.googleapis.com")
public interface GoogleAuthClient {

	@PostMapping("/token")
	GoogleOAuthToken getGoogleAccessToken(
			@RequestHeader ("Content-Type") String contentType,
			@RequestParam String code,
			@RequestParam String clientId,
			@RequestParam String clientSecret,
			@RequestParam String redirectUri,
			@RequestParam String grantType
	);


}
