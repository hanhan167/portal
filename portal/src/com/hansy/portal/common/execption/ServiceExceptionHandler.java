package com.hansy.portal.common.execption;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ServiceExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
		Log log = LogFactory.getLog(ServiceExceptionHandler.class);
		 log.error("ERROR", exception);
		Map<String, Object> model = new HashMap<String, Object>();
		 if(exception instanceof ServiceException) {  
			 	model.put("code", ((ServiceException) exception).getCode());
			 	model.put("msg", ((ServiceException) exception).getMsg());
	            return new ModelAndView("error/exeception", model);  
	        } else {  
	            return new ModelAndView("error", model);  
	        }  
	}

}
