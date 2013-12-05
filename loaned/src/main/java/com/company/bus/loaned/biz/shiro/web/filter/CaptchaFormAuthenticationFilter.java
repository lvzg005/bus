package com.company.bus.loaned.biz.shiro.web.filter;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;

import com.company.bus.loaned.biz.shiro.web.token.CaptchaUsernamePasswordToken;

/**
 * @Description 在CustomFormAuthenticationFilter基础上整合验证码
 * @author 
 * @date 2013-2-26
 * @version 1.0
 */
public class CaptchaFormAuthenticationFilter extends
		CustomFormAuthenticationFilter {

	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) {
		//获取用户名
		String strUsername = request.getParameter(usernameParamName);
		//获取密码
		String strPassword = request.getParameter(passwordnameParamName);
		//获取验证码
		String strCaptcha = request.getParameter(captchaParamName);
		
		boolean bRememberMe = isRememberMe(request);
		
		String host = getHost(request);

		return new CaptchaUsernamePasswordToken(strUsername, strPassword, 
				bRememberMe, host, strCaptcha);
	}
	
	private String usernameParamName;
	
	private String passwordnameParamName;
	
	private String captchaParamName;

	public void setUsernameParamName(String usernameParamName) {
		this.usernameParamName = usernameParamName;
	}

	public void setPasswordnameParamName(String passwordnameParamName) {
		this.passwordnameParamName = passwordnameParamName;
	}

	public void setCaptchaParamName(String captchaParamName) {
		this.captchaParamName = captchaParamName;
	}

}
