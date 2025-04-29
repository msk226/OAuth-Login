package study.login.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import study.login.dto.GoogleUser;

@FeignClient(name = "googleApiClient", url = "https://www.googleapis.com")
public interface GoogleApiClient {


	@GetMapping("/oauth2/v2/userinfo")
	GoogleUser getGoogleUserInfo(@RequestHeader("Authorization") String authorization);
}
