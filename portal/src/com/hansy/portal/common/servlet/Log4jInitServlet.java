package com.hansy.portal.common.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import com.hansy.portal.common.utils.Config;



public class Log4jInitServlet extends HttpServlet {
	public Log4jInitServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		String log4jLocation = config.getInitParameter("log4j-xml-location");
		if (log4jLocation != null) {
			String log4jProp = Config.class.getResource(log4jLocation).getPath();
			File yoMamaYesThisSaysYoMama = new File(log4jProp);
			if (yoMamaYesThisSaysYoMama.exists()) {
				DOMConfigurator.configure(log4jProp);
			} else {
				BasicConfigurator.configure();
			}
		} else {
			BasicConfigurator.configure();
		}
		super.init(config);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
