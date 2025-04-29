package study.login.client.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import study.login.dto.kakao.KaKaoUser;

@FeignClient(name = "kakaoApiClient", url = "https://kapi.kakao.com")
public interface KaKaoApiClient {

	@GetMapping("/v2/user/me")
	KaKaoUser getKaKaoUserInfo(
			@RequestHeader("Authorization") String accessToken,
			@RequestHeader("Content-Type") String contentType);
}
