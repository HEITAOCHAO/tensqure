package com.tensquare.user.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("经过拦截器！");
		try {
			String token = request.getHeader("token");
			Claims claims = jwtUtil.parser(token);
			if(claims.get("roles").equals("admin")){
				request.setAttribute("admin_claims", claims);
			}else if(claims.get("roles").equals("user")){
				request.setAttribute("user_claims", claims);
			}
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
