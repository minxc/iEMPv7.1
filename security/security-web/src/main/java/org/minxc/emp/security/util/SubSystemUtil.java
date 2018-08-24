package org.minxc.emp.security.util;

import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.rest.util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubSystemUtil {

    /**
     * 获取上下文系统ID
     *
     * @param req
     * @return
     */
    public static String getSystemId(HttpServletRequest req) {
        String systemId = CookieUtil.getValueByName("systemId", req);
        if (StringUtil.isEmpty(systemId)) return "1";
        return systemId;
    }

    /**
     * 设置系统id。
     *
     * @param req
     * @param response
     * @param systemId
     */
    public static void setSystemId(HttpServletRequest req, HttpServletResponse response, String systemId) {
        CookieUtil.addCookie("systemId", systemId, true, req, response);
    }
}
