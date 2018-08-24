
/**  

* @Title: CookieUtil.java 

* @Package com.minxc.emp.core.util 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年8月24日 下午12:57:49 

* @version V1.0  

*/ 

package com.minxc.emp.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**      
* 项目名称：core-impl   
* 类名称：CookieUtil   
* 类描述：   Cookie工具类
* 创建人：Xianchang.min   
* 创建时间：2018年8月24日 下午12:57:49   
* 修改人：Xianchang.min   
* 修改时间：2018年8月24日 下午12:57:49   
* 修改备注：   
* @version  1.0  
**/

public class CookieUtil {

    private CookieUtil() {
    }

    /**
     * 添加cookie
     * 
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    /**
     * 删除cookie
     * 
     * @param response
     * @param name
     */
    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie uid = new Cookie(name, null);
        uid.setPath("/");
        uid.setMaxAge(0);
        response.addCookie(uid);
    }

    /**
     * 获取cookie值
     * 
     * @param request
     * @return
     */
    public static String getValue(HttpServletRequest request,String cookieName) {
        Cookie cookies[] = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}