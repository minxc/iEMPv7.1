package org.minxc.emp.security.login.logout;

import com.alibaba.fastjson.JSONObject;
import org.minxc.emp.base.api.constant.BaseStatusCode;
import org.minxc.emp.base.api.response.impl.ResultMsg;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        response.setCharacterEncoding("UTF-8");

        ResultMsg resultMsg = new ResultMsg(BaseStatusCode.SUCCESS, "退出登录成功");
        response.getWriter().print(JSONObject.toJSONString(resultMsg));
        return;
    }

}
