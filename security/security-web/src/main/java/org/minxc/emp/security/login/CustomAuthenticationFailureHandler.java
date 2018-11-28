package org.minxc.emp.security.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.security.constant.PlatFormStatusCode;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.alibaba.fastjson.JSONObject;
/**
  *  登录失败处理器
 * @author Xianchang.min 2018-11-24
 *
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type", "text/plain;charset=UTF-8");
		if(exception instanceof BadCredentialsException) {
			ResultMessage<String>  msg = new ResultMessage<>(PlatFormStatusCode.LOGIN_ERROR, "用户名或者密码错误");
			response.getWriter().print(JSONObject.toJSONString(msg));
		}else {
			ResultMessage<String>  msg = new ResultMessage<>(PlatFormStatusCode.LOGIN_ERROR, "登录失败,请核对您的信息");
			response.getWriter().print(JSONObject.toJSONString(msg));
		}
	}

}
