package com.zwy.controllers;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zwy.entity.User;
import com.zwy.returns.ReturnCode;
import com.zwy.returns.ReturnObject;
import com.zwy.services.UserService;
import com.zwy.util.Base64;
import com.zwy.util.Constant;
import com.zwy.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ReturnObject login(HttpServletRequest request,HttpServletResponse response,@RequestBody User user) throws Exception {
		if(user == null)return new ReturnObject(ReturnCode.C401);
		User authUser = userService.getUserByUserName(user.getUsername());
		if(authUser == null)return new ReturnObject(ReturnCode.C236);
		if(!authUser.getPassword().equals(user.getPassword()))return new ReturnObject(ReturnCode.C210);
		String subject = JwtUtil.generalSubject(authUser);
		String access_token = jwtUtil.createJWT(Constant.JWT_ID, subject, Constant.JWT_TTL);
		access_token = Base64.encode(access_token.getBytes());
		String refresh_access_token = jwtUtil.createJWT(Constant.JWT_ID, subject, Constant.JWT_REFRESH_TTL);
		refresh_access_token = Base64.encode(refresh_access_token.getBytes());
		authUser.setAccess_token(access_token);
		authUser.setExpires_in(Constant.JWT_TTL);
		authUser.setRefresh_access_token(refresh_access_token);
		authUser.setRefresh_expires_in(Constant.JWT_REFRESH_TTL);
		authUser.setPassword(null);
		System.out.println("subject =  " + subject + ", access_token = " + URLEncoder.encode(authUser.getAccess_token(), "UTF-8") + ",refresh_access_token = " + refresh_access_token);
		Cookie token_cookie = new Cookie("fcct", URLEncoder.encode(authUser.getAccess_token(), "UTF-8"));
	    token_cookie.setPath("/");
	    token_cookie.setMaxAge(7*60*60*60);
	    token_cookie.setHttpOnly(true);
        response.addCookie(token_cookie);
		return new ReturnObject(ReturnCode.C200, authUser);
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ReturnObject info(HttpServletRequest request) {
		User authUser = (User) request.getAttribute("userContext");
		return new ReturnObject(ReturnCode.C200, authUser);
	}
}
