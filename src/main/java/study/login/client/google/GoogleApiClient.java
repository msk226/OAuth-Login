package study.login.client.google;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import study.login.dto.google.GoogleUser;

@FeignClient(name = "googleApiClient", url = "https://www.googleapis.com")
public interface GoogleApiClient {


	@GetMapping("/oauth2/v2/userinfo")
	GoogleUser getGoogleUserInfo(@RequestHeader("Authorization") String authorization);
}
