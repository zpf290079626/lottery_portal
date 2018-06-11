package com.zpf.controller.login;

import com.zpf.constants.CommonConstants;
import com.zpf.domain.common.MailParamDomain;
import com.zpf.domain.login.UserContext;
import com.zpf.domain.login.UserLoginVO;
import com.zpf.domain.login.UserRegisterDomain;
import com.zpf.service.login.ILoginService;
import com.zpf.service.mail.ISendMailService;
import com.zpf.utils.DateUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @ClassName: LoginController 
 * @Description: TODO
 * @author: zhangpengfei E-mail:pengfei19890227@163.com
 * @date: 2017年8月25日 下午10:11:38  
 */
@Controller
@RequestMapping("/loginController")
public class LoginController {

	private static final String MAIN_PAGE = "main/loginSuccess";
	private static final String LOGIN_PAGE_RETURN = "login/error";
	private static final String REGISTER = "login/register";
	private static final String LOGIN = "login/login";
	private static final Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	public ILoginService loginService;
	@Autowired
	public ISendMailService sendMailService;

	/**
	 * 跳转到登录页面
	 */
	@RequestMapping("/gotoLogin")
	public String gotoLogin(ModelMap modelMap) {

		return "login/login";
	}

	/**
	 * 系统登录
	 * 
	 * @param request
	 * @param loginVO
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView doLogin(HttpServletRequest request, UserLoginVO loginVO) {

		try {
			if (StringUtils.isEmpty(loginVO.getUserId())) {
				ModelAndView mav = new ModelAndView(LOGIN_PAGE_RETURN);
				mav.addObject("loginError", "登录失败,用户名不能为空!");
				return mav;
			} else if (StringUtils.isEmpty(loginVO.getPassword())) {
				ModelAndView mav = new ModelAndView(LOGIN_PAGE_RETURN);
				mav.addObject("loginError", "登录失败,密码不能为空!");
				return mav;
			}

			HttpSession session = request.getSession();
			session.removeAttribute("url");
			UserContext loginUserContext = (UserContext) session.getAttribute(CommonConstants.USER_CONTEXT_IN_SESSION);
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName(MAIN_PAGE);
			if (loginUserContext != null) { // TODO 删除在线用户列表
				session.removeAttribute(CommonConstants.USER_CONTEXT_IN_SESSION);
			}

			UserContext userContext = loginService.login(loginVO);
			if (userContext.getLoginErrorMsg() != null) {
				modelView.setViewName(LOGIN_PAGE_RETURN);
				modelView.addObject("loginError", userContext.getLoginErrorMsg());
				return modelView;
			}
			session.setAttribute(CommonConstants.SESSION_ONLINE_USER, userContext);
			userContext.setLoginIp(request.getRemoteAddr());
			userContext.setSessionId(session.getId());
			return modelView;
		} catch (DataIntegrityViolationException e) {
			ModelAndView mav = new ModelAndView(LOGIN_PAGE_RETURN);
			mav.addObject("loginError", "登录失败,此用户不存在!");
			logger.error("登录失败", e);
			return mav;
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView(LOGIN_PAGE_RETURN);
			mav.addObject("loginError", "登录失败,请重试!");
			logger.error("登录失败", e);
			return mav;
		}
	}

	/**
	 * 用户注册
	 * 
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView doRegister() {
		ModelAndView mav = new ModelAndView(REGISTER);
		return mav;

	}

	/**
	 * 获得验证码
	 * 
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/obtainValiCode", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getObtainValiCode(HttpServletRequest request) {
		String result = "";
		String emailAddress = request.getParameter("email");
		logger.info("前台提交email地址：" + emailAddress);
		MailParamDomain mail = new MailParamDomain();
		mail.setSubject(CommonConstants.EMAIL_SUBJECT_VALID);
		mail.setTo(emailAddress);
		int validCode = (int) (Math.random() * 1000000);
		mail.setContent("本次注册时间为：" + DateUtil.getMachingCurrentTime() + " 本次注册验证码为：" + validCode + " （五分钟内有效）");

		HttpSession session = request.getSession();
		session.setAttribute(CommonConstants.SESSION_VALIDCODE, String.valueOf(validCode));
		session.setAttribute(CommonConstants.SESSION_VALIDCODE_TIME, DateUtil.getMachingCurrentTime());

		result = sendMailService.sendMail(mail);

		if (StringUtils.isBlank(result)) {
			result = "验证码已发送至用户注册邮箱：" + emailAddress + " 请在五分钟内验证，过期需要重新验证！";
		}
		return result;
	}

	@RequestMapping(value = "/userRegister", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView userRegister(HttpServletRequest request, UserRegisterDomain userRegisterDomain) {
		ModelAndView modelAndView = new ModelAndView(REGISTER);
		modelAndView.addObject("registerError", "注册失败!");
		HttpSession session = request.getSession();
		String sys_validCode = (String) session.getAttribute(CommonConstants.SESSION_VALIDCODE);
		String sys_validCodeTime = (String) session.getAttribute(CommonConstants.SESSION_VALIDCODE_TIME);

		if (null == sys_validCode || !sys_validCode.equals(userRegisterDomain.getInputValiCode())) {
			modelAndView.addObject("registerError", "邮箱验证码错误!");
			return modelAndView;
		}
		String nextTime = DateUtil.nextOrBeforPriodTime(sys_validCodeTime, 5, "m");
		if (nextTime.compareTo(DateUtil.getMachingCurrentTime()) < 0) { // 验证码失效
			modelAndView.addObject("registerError", "邮箱验证码已超过有效期!");
			return modelAndView;
		}

		String errMsg = loginService.register(userRegisterDomain);
		if (StringUtils.isBlank(errMsg)) {
			modelAndView = new ModelAndView(LOGIN);
			modelAndView.addObject("registerSuccess", "用户注册成功!");
		}

		return modelAndView;
	}

	// /**
	// * 系统登出
	// *
	// * @param request
	// * @param loginVO
	// * @return
	// */
	// @RequestMapping(value = "/logout")
	// public ModelAndView doLogout(HttpServletRequest request) {
	// try {
	// HttpSession session = request.getSession();
	// session.removeAttribute(CommonConstants.SESSION_ONLINE_USER);
	// session.invalidate();
	// } catch (Exception e) {
	// logger.error(e.getMessage(), e);
	// }
	// ModelAndView mav = new ModelAndView(new RedirectView(LOGIN_PAGE));
	// return mav;
	// }

	// /**
	// * 为当前session(缓存中读取)设置用户信息
	// */
	// private void setCacheSessionUserInfo(HttpServletRequest request,
	// UserContext userContext) {
	// try {
	// // 缓存对象
	// CacheManagerBL cacheManagerBL = CacheManagerBL.getInstance();
	// Cache ehCache = cacheManagerBL.getEhCache();
	// Element element = ehCache.get(CacheConstants.SESSION_CACHE);
	// if (element != null) {
	// // 当前sessionid
	// String session_id = request.getSession().getId();
	// @SuppressWarnings("unchecked")
	// HashMap<String, SessionDomain> map = (HashMap<String, SessionDomain>)
	// element.getObjectValue();
	// // 设置缓存中的sessionid对应信息
	// SessionDomain sDomain = map.get(session_id);
	// if (sDomain != null) {
	// sDomain.setUserName(userContext.getUserName());
	// sDomain.setUserId(userContext.getUserId());
	// sDomain.setRemoteAddr(request.getRemoteAddr());
	// ehCache.put(element);
	// }
	// }
	// } catch (Exception e) {
	// logger.error(e.getMessage(), e);
	// }
	// }

}
