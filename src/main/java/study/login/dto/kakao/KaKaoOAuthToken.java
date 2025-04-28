package study.login.dto.kakao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class KaKaoOAuthToken {

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KaKaoOAuthTokenDTO {
        private String token_type;
        private String access_token;
        private String refresh_token;
        private String expires_in;
        private String refresh_token_expires_in;
    }
}
