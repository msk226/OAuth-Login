package study.login.security;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {

	@Value("${jwt.secret-key}")
	private String JWT_SECRET_KEY;

	/*
	JWT 생성
	@param userId
	@return String
	 */
	public String createJwt(Long userId){
		Date now = new Date();
		return Jwts.builder()
				.setHeaderParam("type","jwt")
				.claim("userIdx",userId)
				.setIssuedAt(now)
				.setExpiration(new Date(System.currentTimeMillis()+1*(1000*60*60*24*365)))
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
				.compact();
	}

	/*
	Header에서 X-ACCESS-TOKEN 으로 JWT 추출
	@return String
	 */
	public String getJwt(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		return request.getHeader("X-ACCESS-TOKEN");
	}

	/*
	JWT에서 userId 추출
	@return Long
	@throws BaseException
	 */
	public Long getUserId() throws RuntimeException{
		//1. JWT 추출
		String accessToken = getJwt();
		if(accessToken == null || accessToken.length() == 0){
			throw new RuntimeException();
		}

		// 2. JWT parsing
		Jws<Claims> claims;
		try{
			claims = Jwts.parser()
					.setSigningKey(JWT_SECRET_KEY)
					.parseClaimsJws(accessToken);
		} catch (Exception ignored) {
			throw new RuntimeException();
		}

		// 3. userIdx 추출
		return claims.getBody().get("userIdx",Long.class);
	}

	public void isUserValid(Long userId) {
		if (!Objects.equals(userId, getUserId())){
			throw new RuntimeException();
		}
	}
}