package com.ktdsuniversity.edu.global.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ktdsuniversity.edu.domain.user.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AccessControlInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();  
        UserVO loginUser = (UserVO)request.getSession().getAttribute("__LOGIN_USER__");
        String auth = null;
        if(loginUser != null) {
        	auth = loginUser.getAutr();
        }
        
        //관리자 페이지는 관리자(1001)만 접근가능
        if (uri.startsWith("/admin") && !"1001".equals(auth)) {
            response.sendRedirect("/not-admin");
            return false;
        }
        //광고주 고유 권한은 광고주, 관리자(1004, 1001) 접근가능
        if(uri.startsWith("/adv") && (!"1001".equals(auth) && !"1004".equals(auth))) {
        	return false;
        }
        //블로거 고유 권한은 블로거, 관리자(1002, 1003, 1004) 접근 가능 
        if((uri.startsWith("/blog") || uri.startsWith("/blgr")) 
            && (!"1002".equals(auth) && !"1003".equals(auth) && !"1004".equals(auth))) {
        	return false;
        }
        return true;
	}
	
}
