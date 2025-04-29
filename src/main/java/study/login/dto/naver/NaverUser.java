package study.login.dto.naver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NaverUser {

	String resultcode;
	String message;
	NaverPropertiesDTO response;



	@Getter
	@Setter
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	public static class NaverPropertiesDTO {
		String id;
		String name;
		String email;
		String thumbnail_image;
	}


}
