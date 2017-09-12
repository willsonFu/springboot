package com.zwy.springboot;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("Session的创建");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("Session的销毁");
	}

}
