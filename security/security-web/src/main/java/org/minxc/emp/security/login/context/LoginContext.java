package org.minxc.emp.security.login.context;

import com.minxc.emp.core.util.AppContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.idm.api.context.CurrentContext;
import org.minxc.emp.idm.api.model.Group;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.idm.api.service.GroupService;
import org.minxc.emp.idm.api.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.Resource;
import java.util.Locale;


@Slf4j
public class LoginContext implements CurrentContext {

    /**
     * 存放当前用户登录的语言环境
     */
    private static ThreadLocal<Locale> currentLocale = new ThreadLocal<Locale>();
    /**
     * 当前上下文用户。
     */
    private static ThreadLocal<User> currentUser = new ThreadLocal<User>();
    /**
     * 当前上下文用户。
     */
    private static ThreadLocal<Group> currentOrg = new ThreadLocal<Group>();

    @Resource
    GroupService groupService;
    @Resource
    UserService userService;

    /**
     * 获取当前语言环境
     *
     * @return
     */
    public Locale getLocale() {
        if (currentLocale.get() != null) {
            return currentLocale.get();
        }
        setLocale(new Locale("zh", "CN"));
        return currentLocale.get();
    }

    /**
     * 返回当前用户ID
     *
     * @return String
     */
    public String getCurrentUserId() {
        User user = getCurrentUser();
        if (user == null) return null;
        return user.getUserId();
    }

    public User getCurrentUser() {
        if (currentUser.get() != null) {
            User user = currentUser.get();
            return user;
        }
        SecurityContext securityContext = SecurityContextHolder.getContext();

        if (securityContext == null) return null;

        Authentication auth = securityContext.getAuthentication();

        if (auth == null) return null;

        Object principal = auth.getPrincipal();
        if (principal != null && principal instanceof User) {
            return (User) principal;
        }

        return null;
    }


    public Group getCurrentGroup() {
        if (currentOrg.get() != null) {
            return currentOrg.get();
        }
        String userId = getCurrentUserId();
        //从缓存中取
        Cache Cache = (Cache) AppContextUtil.getBean(Cache.class);
        String userKey = CurrentContext.CURRENT_ORG + userId;
        if (Cache.containKey(userKey)) {
            return (Group) Cache.getByKey(userKey);
        }
        //获取当前人的主部门
        Group group = groupService.getMainGroup(userId);
        if (group != null) setCurrentGroup(group);
        return group;
    }

    /**
     * 将当前组织放到线程变量和缓存中
     */
    public void setCurrentGroup(Group group) {
        currentOrg.set(group);
        //将当前人和组织放到缓存中。
        String userId = getCurrentUserId();
        Cache Cache = (Cache) AppContextUtil.getBean(Cache.class);
        String userKey = CurrentContext.CURRENT_ORG + userId;
        Cache.add(userKey, group);
    }

    /**
     * 清理线程中的用户变量，以及他的岗位信息
     */
    public void clearCurrentUser() {
        currentUser.remove();
        currentOrg.remove();
    }

    public void setCurrentUser(User user) {
        currentUser.set(user);
    }

    public void clearLocale() {
        currentLocale.remove();
    }

    public void setLocale(Locale local) {
        currentLocale.set(local);
    }

    @Override
    public void setCurrentUserByAccount(String account) {
        if (StringUtils.isEmpty(account)) {
            throw new RuntimeException("输入帐号为空!");
        }
        User user = userService.getUserByAccount(account);
        if (user == null) {
            throw new RuntimeException("系统中没有帐号[" + account + "]对应的用户");
        }
        currentUser.set(user);
    }

}
