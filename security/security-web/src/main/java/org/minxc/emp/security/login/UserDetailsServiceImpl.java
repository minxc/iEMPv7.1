package org.minxc.emp.security.login;

import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.idm.api.model.UserRole;
import org.minxc.emp.idm.api.service.UserService;
import org.minxc.emp.security.constant.PlatformSecurityConstants;
import org.minxc.emp.security.login.model.LoginUser;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 实现UserDetailsService 接口获取UserDetails 接口实例对象。
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;
    
    @Resource(name="securityCacheManager")
    private CacheManager cacheManager;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	
    	
        User defaultUser = userService.getUserByAccount(username);

        if (BeanUtils.isEmpty(defaultUser)) {
            throw new UsernameNotFoundException("用户：" + username + "不存在");
        }

        LoginUser loginUser = new LoginUser(defaultUser);

        //构建用户角色。
        List<UserRole> userRoleList = userService.getUserRole(loginUser.getId());
        Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
        for (UserRole userRole : userRoleList) {
            GrantedAuthority role = new SimpleGrantedAuthority(userRole.getAlias());
            collection.add(role);
        }

        if (ContextUtil.isAdmin(loginUser)) {
            collection.add(PlatformSecurityConstants.ROLE_GRANT_SUPER);
        }
        loginUser.setAuthorities(collection);
        Cache cache = cacheManager.getCache("SPRING_SECURITY_CACHE");
        cache.put(username, loginUser);
        return loginUser;
    }
}
