package study.login.oauth;


import java.io.IOException;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import study.login.common.SocialLoginType;
import study.login.dto.google.GoogleOAuthToken;
import study.login.dto.google.GoogleUser;
import study.login.dto.kakao.KaKaoOAuthToken.KaKaoOAuthTokenDTO;
import study.login.dto.kakao.KaKaoUser;
import study.login.oauth.imple.GoogleOauth;
import study.login.oauth.imple.KaKaoOauth;
import study.login.security.JwtService;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final HttpServletResponse response;
    private final KaKaoOauth kaKaoOauth;
    private final GoogleOauth googleOauth;
    private final JwtService jwtService;

    public void accessRequest(SocialLoginType socialLoginType) throws IOException {
        String redirectURL;

        switch (socialLoginType){ //각 소셜 로그인을 요청하면 소셜로그인 페이지로 리다이렉트 해주는 프로세스이다.
            case GOOGLE:{
                redirectURL = googleOauth.getOauthRedirectURL();
            } break;
            case KAKAO:{
                redirectURL = kaKaoOauth.getOauthRedirectURL();
            } break;
            case NAVER:{
                redirectURL = " ";
            } break;
            case APPLE: {
                redirectURL = " ";
            } break;
            default:{
                throw new RuntimeException();
            }
        }
        response.sendRedirect(redirectURL);
    }


    public void oAuthLoginOrJoin(SocialLoginType socialLoginType, String code) throws IOException {
        switch (socialLoginType) {
            case GOOGLE: {
                GoogleOAuthToken googleOAuthToken = googleOauth.requestAccessToken(code);
                GoogleUser googleUser = googleOauth.requestUserInfo(googleOAuthToken);

            } break;
            case KAKAO: {
                KaKaoOAuthTokenDTO kaKaoOAuthTokenDTO = kaKaoOauth.requestAccessToken(code);
                KaKaoUser kaKaoUser = kaKaoOauth.requestUserInfo(kaKaoOAuthTokenDTO);
            } break;
            case NAVER: {

            } break;
            case APPLE: {

            } break;
            default: {
                throw new RuntimeException();
            }

        }
    }


}
