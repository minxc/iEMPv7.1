package org.minxc.emp.security.authentication;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.security.IngoreChecker;
import org.minxc.emp.security.util.SubSystemUtil;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import org.minxc.emp.security.constant.PlatformSecurityConstants;
import org.minxc.emp.system.api.service.SystemResourceService;

/*

* @Title: FilterInvocationSecurityMetadataSourceImpl

* @Description: 根据当前的URL获取他上面分配的角色列表

*/
public class FilterInvocationSecurityMetadataSourceImpl extends IngoreChecker implements FilterInvocationSecurityMetadataSource {

    @Resource
    private SystemResourceService systemResourceService;

    /**

    * @Title: getAttributes   TODO:增加缓存实现，实现动态更新缓存和数据库信息

    * @Description: 获取请求路径所配置角色等信息

    * @param object 过滤器链信息

    * @return java.util.Collection<org.springframework.security.access.ConfigAttribute>    返回类型

    * @throws   IllegalArgumentException  抛出异常

    **/
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        Collection<ConfigAttribute> configAttribute = new HashSet<ConfigAttribute>();

        FilterInvocation filterInvocation = ((FilterInvocation) object);
        HttpServletRequest request = filterInvocation.getRequest();

        String url = request.getRequestURI();
        url = removeWebContextPath(url, request.getContextPath());

        if (isIngores(url)) {
            configAttribute.add(PlatformSecurityConstants.ROLE_CONFIG_ANONYMOUS);
            return configAttribute;
        }

        String systemId = SubSystemUtil.getSystemId(request);
        Map<String, Set<String>> urlRoleMap = systemResourceService.getUrlRoleBySystem(systemId);
        //根据当前的URL获取资源对应的角色。
        if (urlRoleMap.containsKey(url)) {
            Set<String> urlSet = urlRoleMap.get(url);
            for (String role : urlSet) {
                configAttribute.add(new SecurityConfig(role));
            }
        } else {
            configAttribute.add(PlatformSecurityConstants.ROLE_CONFIG_PUBLIC);
        }
        return configAttribute;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }


    /**
     * 去除web的content路径
     *
     * @param url
     * @param ctxPath
     * @return
     */
    private static String removeWebContextPath(String url, String ctxPath) {
        url = url.trim();
        if (StringUtils.isEmpty(ctxPath)) return url;
        if (StringUtils.isEmpty(url)) return "";
        if (url.startsWith(ctxPath)) {
            url = url.replaceFirst(ctxPath, "");
        }
        return url;
    }

}
