package com.zwy.interceptor;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.zwy.entity.User;
import com.zwy.services.UserService;
import com.zwy.util.Base64;
import com.zwy.util.JwtUtil;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private final static String XAUTH_TOKEN_HEADER_NAME = "x-auth-token";
    
    private final static String XAUTH_TOKEN_PARAMETER_NAME = "auth_token";
    
    public final static String TOKEN_COOKIE_NAME = "fcct";
    
    @Autowired
	private JwtUtil jwtUtil;
    @Autowired
	private UserService userService;
    

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String authTokenCookie = null;
		if(request.getCookies()!=null){
			for (Cookie cookie : request.getCookies()) {
				if(cookie.getName().equals(TOKEN_COOKIE_NAME)){
					authTokenCookie = cookie.getValue();
				}
			}
		}
		String authTokenHeader = request.getHeader(XAUTH_TOKEN_HEADER_NAME);
        String authTokenParameter = request.getParameter(XAUTH_TOKEN_PARAMETER_NAME);
        String authToken = null==authTokenParameter?null==authTokenHeader?authTokenCookie:authTokenHeader:authTokenParameter;
        if(authToken == null)return false;
        authToken = URLDecoder.decode(authToken, "UTF-8");
        authToken = new String(Base64.decode(authToken));
        System.out.println("authToken = " + authToken);
        System.out.println(jwtUtil);
        String subject = jwtUtil.parseJWT(authToken).getSubject();
        JSONObject jsonObj = JSONObject.parseObject(subject);
        User authUser = userService.getUserById(jsonObj.getIntValue("userId"));
        request.setAttribute("userContext", authUser);
		return super.preHandle(request, response, handler);
	}

	
}
