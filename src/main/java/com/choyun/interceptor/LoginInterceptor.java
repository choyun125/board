package com.choyun.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.choyun.domain.MemberVO;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		
		ModelMap map = modelAndView.getModelMap();
		
		MemberVO mvo = (MemberVO) map.get("loginSession");
		
		if (mvo != null) {
			session.setAttribute("loginSession", mvo);
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("loginSession") != null) {
			
			session.setAttribute("loginSession", null);
		}
		return super.preHandle(request, response, handler);
	}
	
}
