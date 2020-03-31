package com.tensquare.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@Component
public class ManagerZuulFilter extends ZuulFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public String filterType() {
		return "pre"; //post=后执行
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * 是否开启过滤器
	 * @return
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext currentContext = RequestContext.getCurrentContext();
		HttpServletRequest request = currentContext.getRequest();

		if(request.getMethod().equals("OPTIONS")){
			return null;
		}

		if(request.getRequestURL().toString().contains("login")){
			return null;
		}

		if(request.getRequestURL().toString().contains("add")){
			return null;
		}

		String token = request.getHeader("token");
		if(token==null||"".equals(token)){
			currentContext.setResponseStatusCode(403);
			currentContext.setResponseBody("无权访问！");
			currentContext.getResponse().setContentType("text/html;charset=utf-8");
			currentContext.setSendZuulResponse(false);//终止运行
			return null;
		}
		try {
			Claims claims = jwtUtil.parser(token);
			if(claims.get("roles").equals("admin")){
				request.setAttribute("admin_claims", claims);
			}else if(claims.get("roles").equals("user")){
				request.setAttribute("user_claims", claims);
			}
			currentContext.addZuulRequestHeader("token", token);
			return null;
		}catch (Exception e){
			currentContext.setResponseStatusCode(500);
			currentContext.setResponseBody("请重新登陆！");
			currentContext.getResponse().setContentType("text/html;charset=utf-8");
			currentContext.setSendZuulResponse(false);//终止运行
			return null;
		}
	}
}
