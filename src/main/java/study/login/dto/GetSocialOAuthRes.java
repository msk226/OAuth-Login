package study.login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.login.common.SocialLoginType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetSocialOAuthRes {

	private String jwtToken;
	private Long userId;
	private String accessToken;
	private String tokenType;
	private SocialLoginType socialLoginType;
}
