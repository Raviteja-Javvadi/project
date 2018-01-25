package com.occ.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.occ.RuntimeExec.RuntimeExec;
import com.occ.filewriter.FWriter;
import com.occ.procbuilder.ProcBuilder;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final String COMPILATION_SUCCESSFUL = "Compilation successful...";

	private static final long serialVersionUID = 1L;

	private static final String FILEPATHC = "/home/rc/tomcat/upload/c/test.c";
	private static final String FILEPATHCPP = "/home/rc/tomcat/upload/cpp/test.cpp";
	private static final String FILEPATHJAVA = "/home/rc/tomcat/upload/java/Test.java";
	private static final String FILEPATHPYTHON = "/home/rc/tomcat/upload/python/test.py";

	private static final String RUNCMDC = "/home/rc/tomcat/upload/c/a.out";
	private static final String RUNCMDCPP="/home/rc/tomcat/upload/cpp/a.out";
	private static final String RUNCMDJAVA="java /home/rc/tomcat/upload/cpp/Test.class";
	private static final String RUNCMDPYTHON="";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("hello test");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer message = null;
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String code = request.getParameter("code");
		String lang = request.getParameter("lang");
		System.out.println("The Language selected is " + lang);
		if (lang.equals("C")) {

			message = compileExecute(code, FILEPATHC,"/home/rc/tomcat/upload/c/");

		} else if (lang.trim().equals("C++")) {

			message = compileExecute(code, FILEPATHCPP,"/home/rc/tomcat/upload/cpp/");

		} else if (lang.equals("JAVA")) {

			message = compileExecute(code, FILEPATHJAVA,"java","Test","/home/rc/tomcat/upload/java");

		} else if (lang.equals("PYTHON")) {

			message = compileExecute(code, FILEPATHPYTHON,"/home/rc/tomcat/upload/python");

		}

		request.setAttribute("code", code);
		request.setAttribute("message", message); // This will be available as ${message}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private StringBuffer compileExecute(String code, String path, String... cmd) throws IOException {

		FWriter fWriter = new FWriter();
		fWriter.write(code, path);

		StringBuffer message = null;
		try {
			message = new StringBuffer(ProcBuilder.main(cmd[0], cmd[1], cmd[2]));

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (message.toString().equals(COMPILATION_SUCCESSFUL)) {
			RuntimeExec exec = new RuntimeExec();
			message.append(exec.execute(RUNCMDC));

		}
		return message;
	}
	
}
