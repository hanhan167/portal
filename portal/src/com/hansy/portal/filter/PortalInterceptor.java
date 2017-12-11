package com.hansy.portal.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hansy.portal.model.bo.TUserBaseInfoBo;

public class PortalInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest req,HttpServletResponse resp, Object obj, Exception e)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object obj) throws Exception {
		HttpSession session = req.getSession();
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		if(baseInfoVo == null){
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return false;
		}
		return true;
	}

}
