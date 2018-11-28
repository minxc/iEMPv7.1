package org.minxc.emp.security.forbidden;

import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.core.api.status.CommonStatusCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 返回无权限
 */
public class DefaultAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        ResultMessage ResultMessage = new ResultMessage(CommonStatusCode.NO_ACCESS, ex.getMessage());
        response.getWriter().print(JSONObject.toJSONString(ResultMessage));
        return;
    }

}