package com.javaee.cdi.injectionpoint;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.servlet.http.HttpServletRequest;

@RequestScoped
public class HttpParamProducer {
	
	@Produces
	@HttpParam("")
	@Dependent
	public String getParamValue(InjectionPoint ip, HttpServletRequest req) {
		return req.getParameter(ip.getAnnotated().getAnnotation(HttpParam.class).value());
	}

}
