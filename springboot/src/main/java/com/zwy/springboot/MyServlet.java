package com.zwy.springboot;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns="/myServlet1/*")
public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = -2991612664477376442L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">>>>>>>>>>doGet()<<<<<<<<<<<");
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">>>>>>>>>>doPost()<<<<<<<<<<<");
		HttpSession session = req.getSession();
        resp.setContentType("text/html"); 
        PrintWriter out = resp.getWriter(); 
        out.println("<html>"); 
        out.println("<head>"); 
        out.println("<title>Hello World</title>"); 
        out.println("</head>"); 
        out.println("<body>"); 
        out.println("<h1>这是：MyServlet1,sessionId=" + session.getId() + "</h1>"); 
        out.println("</body>"); 
        out.println("</html>");
	}

	
}
