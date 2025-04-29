package study.login.client.naver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import study.login.dto.naver.NaverUser;

@FeignClient(name = "naverApiClient", url = "https://openapi.naver.com")
public interface NaverApiClient {

	@GetMapping("/v1/nid/me")
	NaverUser getNaverUserInfo(
			@RequestHeader("Authorization") String accessToken);
}
