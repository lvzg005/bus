package com.company.bus.loaned.biz.shiro.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.company.bus.loaned.common.web.controller._BaseController;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


/**
 * @Description 验证码控制器
 * @author 
 * @date 2013-2-26
 * @version 1.0
 */
public class CaptchaController extends _BaseController {

	/**
	 * 验证码处理
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void handleCaptcha(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		
		byte[] captchaChallengeAsJpeg = null;
		
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		
		String captchaId = session.getId();
		log.debug("captcha id: {}", new Object[]{captchaId});

		BufferedImage challenge = captchaService.getImageChallengeForID(captchaId, request.getLocale());
		
		JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);
		jpegEncoder.encode(challenge);
		
		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
		
		ServletOutputStream out = response.getOutputStream();
		out.write(captchaChallengeAsJpeg);
		out.flush();
		out.close();
	}

	protected transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	private ImageCaptchaService captchaService;

	public void setCaptchaService(ImageCaptchaService captchaService) {
		this.captchaService = captchaService;
	}
}
