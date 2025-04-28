package study.login.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import study.login.common.SocialLoginType;
import study.login.dto.GetSocialOAuthRes;
import study.login.oauth.OAuthService;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class LoginController {

	private final OAuthService oAuthService;

	/**
	 * 유저 소셜 가입, 로그인 인증으로 리다이렉트 해주는 url
	 * [GET] /app/users/auth/:socialLoginType/login
	 * @return void
	 */
	@GetMapping("/auth/{socialLoginType}/login")
	public void socialLoginRedirect(@PathVariable(name = "socialLoginType") String SocialLoginPath) throws IOException {
		SocialLoginType socialLoginType= SocialLoginType.valueOf(SocialLoginPath.toUpperCase());
		oAuthService.accessRequest(socialLoginType);
	}


	/**
	 * Social Login API Server 요청에 의한 callback 을 처리
	 * @param socialLoginPath (GOOGLE, FACEBOOK, NAVER, KAKAO)
	 * @param code API Server 로부터 넘어오는 code
	 * @return SNS Login 요청 결과로 받은 Json 형태의 java 객체 (access_token, jwt_token, user_num 등)
	 */
	@GetMapping(value = "/auth/{socialLoginType}/login/callback")
	public GetSocialOAuthRes socialLoginCallback(

			@PathVariable(name = "socialLoginType")  String socialLoginPath,
			@RequestParam(name = "code") String code) throws IOException, RuntimeException{
		log.info(">> 소셜 로그인 API 서버로부터 받은 code : {}", code);
		SocialLoginType socialLoginType = SocialLoginType.valueOf(socialLoginPath.toUpperCase());
		oAuthService.oAuthLoginOrJoin(socialLoginType,code);
		return null;
	}
}
