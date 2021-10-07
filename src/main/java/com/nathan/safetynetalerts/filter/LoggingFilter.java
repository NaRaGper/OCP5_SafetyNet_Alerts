package com.nathan.safetynetalerts.filter;

import java.io.IOException;
import java.net.http.HttpResponse;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LoggingFilter implements Filter {

	private static final Logger log = LogManager.getLogger(LoggingFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		logRequest(httpRequest);

		chain.doFilter(request, response);

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		logResponse(httpResponse);
	}

	private void logRequest(HttpServletRequest httpRequest) {
		log.trace("URL : " + httpRequest.getRequestURL() 
		+ ", Method : " + httpRequest.getMethod());
		//pas de getParameters disponible
	}

	private void logResponse(HttpServletResponse httpResponse) {
		log.trace("Status : " + httpResponse.getStatus());
		//pas de getBody disponible
	}
}


