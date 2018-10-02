package org.minxc.emp.common.rest.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
* 项目名称：base-rest   
* 类名称：CookieUtil   
* 类描述： cookie操作类 ,实现对cookie进行增查改删
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午11:08:35   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午11:08:35   
* 修改备注：   
* @version  1.0  
*
 */
public class CookieUtil {

    /**
     * 添加cookie，cookie的生命周期为关闭浏览器即消失
     *
     * @param name
     * @param value
     * @param req
     * @param response
     */
    public static void addCookie(String name, String value, boolean httpOnly, HttpServletRequest req, HttpServletResponse response) {
        addCookie(name, value, -1, httpOnly, "", req.getContextPath(), req, response);
    }

    /**
     * 添加cookie
     *
     * @param name     cookie名称
     * @param value    cookie值
     * @param maxAge   cookie存活时间
     * @param req
     * @param response
     */
    public static void addCookie(String name, String value, int maxAge, boolean httpOnly, String domain, String path, HttpServletRequest req, HttpServletResponse response) {

        if (response == null) return;
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append('=');
        sb.append(value.trim() + "; ");

        if (maxAge != -1) {
            sb.append("max-age=");
            sb.append(maxAge + "; ");
        }

        //--domain字符串
        if (StringUtils.isNotEmpty(domain)) {
            sb.append("domain=");
            sb.append(domain + "; ");
        }
        //--构造path字符串
        if (StringUtils.isNotEmpty(path)) {
            sb.append("path=");
            sb.append(path + ";");
        }

        //--构造httponly属性
        if (httpOnly) {
            sb.append("HttpOnly");
        }
        response.addHeader("Set-Cookie", sb.toString());
    }


    /**
     * 删除cookie
     *
     * @param name
     * @param response
     */
    public static void delCookie(String name, HttpServletRequest request, HttpServletResponse response) {
        addCookie(name, "", 0, true, "", request.getContextPath(), request, response);
    }


    /**
     * 根据cookie名称取得值
     *
     * @param name
     * @param request
     * @return
     */
    public static String getValueByName(String name, HttpServletRequest request) {
        if (request == null) return "";
        Cookie cookies[] = request.getCookies();
        Cookie sCookie = null;
        String svalue = null;
        String sname = null;

        if (cookies == null)
            return null;
        for (int i = 0; i < cookies.length; i++) {
            sCookie = cookies[i];
            sname = sCookie.getName();
            if (sname.equals(name)) {
                svalue = sCookie.getValue();
                break;
            }
        }
        return svalue;
    }


    /**
     * @param name
     * @param request
     * @return
     */
    public static boolean isExistByName(String name, HttpServletRequest request) {

        Cookie cookies[] = request.getCookies();
        Cookie sCookie = null;

        String sname = null;
        boolean isExist = false;
        if (cookies == null)
            return false;
        for (int i = 0; i < cookies.length; i++) {
            sCookie = cookies[i];
            sname = sCookie.getName();
            if (sname.equals(name)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

}
