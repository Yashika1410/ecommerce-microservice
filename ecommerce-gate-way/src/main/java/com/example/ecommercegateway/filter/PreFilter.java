package com.example.ecommercegateway.filter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreFilter extends ZuulFilter {

	private static final String LOGIN_URL = "/api/login";
//	private static final String OMS_URL = "/oms";
	private static final String CUSTOMER_URL = "/api/customer";
//	private static final String PRODUCTS_URL = "/inventory/txn/list";

	private static final String X_USER_ID = "X_USER_ID";

	private static final String X_USERNAME = "X_USERNAME";

	private static final String X_ROLE = "X_AUTHORITY";

	private static final String X_LOCATION = "X_LOCATION";
	
	private static final String AUTH_HEADER = "Authorization";
	
	public static final String TOKEN_PREFIX = "Bearer ";


	private static final String[] HEADERS = { "Authorization", "Content-Type" };

	private static final String[] NO_AUTHENTICATION = { LOGIN_URL,CUSTOMER_URL,"/catalog/master/location" }; // collection can not
																									// have duplicate
																									// URL

	private static Logger log = LoggerFactory.getLogger(PreFilter.class);

	
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		log.info("Request Filtration Process");
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();

		log.info("Method : {} Request URI : {}", request.getMethod(), request.getRequestURI());
		return null;
	}


	

	
}
