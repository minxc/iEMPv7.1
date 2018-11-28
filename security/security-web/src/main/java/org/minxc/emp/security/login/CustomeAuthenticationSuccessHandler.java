package org.minxc.emp.security.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.security.constant.PlatFormStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.alibaba.fastjson.JSONObject;

public class CustomeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type", "text/plain;charset=UTF-8");
		ResultMessage<String> msg = new ResultMessage<>(PlatFormStatusCode.SUCCESS, "登陆成功");
		response.getWriter().print(JSONObject.toJSONString(msg));
	}
}
