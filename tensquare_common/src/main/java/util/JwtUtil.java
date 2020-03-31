package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

@ConfigurationProperties("jwt.config")
public class JwtUtil {
	//密令
	public String key;
	//过期时间
	public long ttl;

	/**
	 * 创建jwt
	 * @param id
	 * @param subject
	 * @param roles
	 * @return
	 */
	public String createJWT(String id,String subject,String roles){
		return Jwts.builder()
				.setId(id)
				.setSubject(subject)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+ttl))
				.signWith(SignatureAlgorithm.HS256, key)
				.claim("roles", roles)
				.compact();
	}

	/**
	 * 解析token
	 * @param token
	 * @return
	 */
	public Claims parser(String token){
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}


	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public long getTtl() {
		return ttl;
	}

	public void setTtl(long ttl) {
		this.ttl = ttl;
	}
}
