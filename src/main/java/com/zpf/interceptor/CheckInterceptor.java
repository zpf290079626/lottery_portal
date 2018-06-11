package com.zpf.interceptor;

import com.zpf.constants.CommonConstants;
import com.zpf.domain.login.UserContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * 预设用户数据权限到session
 *
 */
public class CheckInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(CheckInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		UserContext userContext = (UserContext) session.getAttribute(CommonConstants.SESSION_ONLINE_USER);
		if (null == userContext || StringUtils.isBlank(userContext.getUserId())) {
			
			logger.info("未登录用户");
			logger.debug(request.getServletPath());
			response.sendRedirect("/index.jsp");
			return false;
		}
		return true;
	}

}
