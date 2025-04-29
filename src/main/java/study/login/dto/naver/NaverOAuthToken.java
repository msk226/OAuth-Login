package study.login.dto.naver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class NaverOAuthToken {

	@Getter
	@Builder
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	public static class NaverOAuthTokenDTO {
		private String access_token;
		private String token_type;
		private String refresh_token;
		private String expires_in;
		private String error;
		private String error_description;
	}

}
