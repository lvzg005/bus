package com.company.bus.loaned.biz.shiro.web.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Description  用户名,密码,验证码令牌
 * @author 
 * @date 2013-2-26
 * @version 1.0
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

	private static final long serialVersionUID = -2406805646378190346L;
	
	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken() {
		super();
	}

	public CaptchaUsernamePasswordToken(String username, String password,
			boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}	
}
