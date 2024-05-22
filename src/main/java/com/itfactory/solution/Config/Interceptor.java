package com.itfactory.solution.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.itfactory.solution.Service.POSTGRES_MES.*;;

public class Interceptor implements HandlerInterceptor {

	 @Autowired
	 POSTGRES_MESService POSTGRES_MESService;
	
	 private Logger logger = Logger.getLogger(POSTGRES_MESService.class);
	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		String URI = request.getRequestURI();
//		String[] uri_array = URI.split("/");
//		if(URI.contains("/back") && !(uri_array[2].toUpperCase()).equals("COMMON")) {
//		}
//		logger.info(" URI ");
//		return false;
//	}

//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//		String URI = request.getRequestURI();
//		
//		if(!URI.contains("/back/Common/")) {
//			System.out.println("==================== END ======================");
//		}
//		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//	}

}
