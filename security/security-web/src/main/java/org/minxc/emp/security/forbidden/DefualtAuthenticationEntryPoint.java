package org.minxc.emp.security.forbidden;

import com.alibaba.fastjson.JSONObject;

import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.core.api.status.CommonStatusCode;
import org.minxc.emp.core.util.JacksonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 超时访问
 *
 * @author jeff
 */
public class DefualtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        response.setCharacterEncoding("UTF-8");

        ResultMessage resultMessage = new ResultMessage(CommonStatusCode.TIMEOUT, authException.getMessage());
        response.getWriter().print(JSONObject.toJSONString(resultMessage));
        return;
    }

}