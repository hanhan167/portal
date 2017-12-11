package com.hansy.portal.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.hansy.portal.common.utils.StringUtil;
import com.hansy.portal.model.bo.TUserBaseInfoBo;
import com.hansy.portal.model.vo.TUserBaseInfoVo;
import com.hansy.portal.service.ITUserBaseInfoService;
import com.hansy.portal.service.impl.TUserBaseInfoServiceImpl;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {
	private TUserBaseInfoServiceImpl TUserBaseInfoServiceImpl;
	

	public TUserBaseInfoServiceImpl getTUserBaseInfoServiceImpl() {
		return TUserBaseInfoServiceImpl;
	}

	public void setTUserBaseInfoServiceImpl(TUserBaseInfoServiceImpl tUserBaseInfoServiceImpl) {
		TUserBaseInfoServiceImpl = tUserBaseInfoServiceImpl;
	}

	/**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 // 获得在下面代码中要用的request,response,session对象
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		TUserBaseInfoBo baseInfoVo=(TUserBaseInfoBo) session.getAttribute("loginUser");
		// 获得用户请求的URI
		String path = servletRequest.getRequestURI();
		String custNo = request.getParameter("custNo");
		if(baseInfoVo!=null){
			if(null!=custNo&&""!=custNo){
				if(!baseInfoVo.getCustNo().equals(custNo)){
					baseInfoVo = null;
				}
			}
		};
		if(path.indexOf("/user/")>-1) {
			if(path.indexOf("/getPwd")<0){
				if (baseInfoVo==null&&custNo==null) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					String url = "<script type='text/javascript'>alert('登录超时请重新登录!');setTimeout(function(){top.location.href='"+"../.."+servletRequest.getContextPath()+"/index.jsp'},1000)</script>";
					out.print(url);
					out.close();
				}else if(baseInfoVo==null&&custNo!=null){
					HttpServletRequest req = (HttpServletRequest)request;
			        ServletContext sc = req.getSession().getServletContext();
			        XmlWebApplicationContext cxt = (XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc);
			        if(cxt != null && cxt.getBean("TUserBaseInfoServiceImpl") != null && TUserBaseInfoServiceImpl == null)
			        	TUserBaseInfoServiceImpl = (TUserBaseInfoServiceImpl) cxt.getBean("TUserBaseInfoServiceImpl");
			        
			        TUserBaseInfoBo userBasic = this.TUserBaseInfoServiceImpl.getBySessionCustNo(custNo);
					if(userBasic==null){
						response.setContentType("text/html;charset=UTF-8");
						PrintWriter out = response.getWriter();
						String url = "<script type='text/javascript'>alert('该用户不存在请注册!');setTimeout(function(){top.location.href='"+"../.."+servletRequest.getContextPath()+"/index.jsp'},1000)</script>";
						out.print(url);
						out.close();
					}else{
						session.setAttribute("loginUser", userBasic);
						chain.doFilter(servletRequest, servletResponse);
					}
				}else{
					chain.doFilter(servletRequest, servletResponse);
				}
			}else{
				chain.doFilter(servletRequest, servletResponse);
			}
		}else{		
			
			
			chain.doFilter(servletRequest, servletResponse);
		}
		return;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
