package com.zpf.service.login;

import com.zpf.domain.login.UserContext;
import com.zpf.domain.login.UserLoginVO;
import com.zpf.domain.login.UserRegisterDomain;

public interface ILoginService {

	public UserContext login(UserLoginVO vo);

	public String register(UserRegisterDomain domain);
}
