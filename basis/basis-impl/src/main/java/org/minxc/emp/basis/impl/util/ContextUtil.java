package org.minxc.emp.basis.impl.util;

import java.util.Locale;

import org.minxc.emp.idm.api.context.CurrentContext;
import org.minxc.emp.idm.api.model.Group;
import org.minxc.emp.idm.api.model.User;

import com.minxc.emp.core.util.BeanUtils;


/**
 * 获取上下文数据对象的工具类。
 */
public class ContextUtil {

    private static ContextUtil contextUtil;

    private CurrentContext currentContext;

    public void setCurrentContext(CurrentContext _currentContext) {
        contextUtil = this;
        contextUtil.currentContext = _currentContext;
    }

    /**
     * 获取当前执行人
     *
     * @return User
     * @throws
     * @since 1.0.0
     */
    public static User getCurrentUser() {
        return contextUtil.currentContext.getCurrentUser();
    }

    public static String getCurrentUserId() {
        return contextUtil.currentContext.getCurrentUserId();
    }

    /**
     * 获取当前组织
     */
    public static Group getCurrentGroup() {
        return contextUtil.currentContext.getCurrentGroup();
    }

    /**
     * 获取当前组织Id。组织为空则返回空
     */
    public static String getCurrentGroupId() {
        Group iGroup = getCurrentGroup();
        if (BeanUtils.isNotEmpty(iGroup)) {
            return iGroup.getGroupId();
        } else {
            return "";
        }
    }

    /**
     * 获取当前Locale。
     *
     * @return Locale
     * @throws
     * @since 1.0.0
     */
    public static Locale getLocale() {
        return contextUtil.currentContext.getLocale();
    }

    /**
     * 清除当前执行人。
     * void
     *
     * @throws
     * @since 1.0.0
     */
    public static void clearCurrentUser() {
        contextUtil.currentContext.clearCurrentUser();

    }

    /**
     * 设置当前执行人。
     *
     * @param user void
     * @throws
     * @since 1.0.0
     */
    public static void setCurrentUser(User user) {
        contextUtil.currentContext.setCurrentUser(user);
    }


    public static void setCurrentUserByAccount(String account) {
        contextUtil.currentContext.setCurrentUserByAccount(account);
    }


    /**
     * 设置当前组织(岗位)。
     *
     * @param user void
     * @throws
     * @since 1.0.0
     */
    public static void setCurrentOrg(Group group) {
        contextUtil.currentContext.setCurrentGroup(group);
    }

    /**
     * 设置Locale。
     *
     * @param locale void
     * @throws
     * @since 1.0.0
     */
    public static void setLocale(Locale locale) {
        contextUtil.currentContext.setLocale(locale);
    }

    /**
     * 清除Local。
     * void
     *
     * @throws
     * @since 1.0.0
     */
    public static void cleanLocale() {
        contextUtil.currentContext.clearLocale();
    }

    public static void clearAll() {
        cleanLocale();
        clearCurrentUser();
    }
    
    public static boolean isAdmin(User user) {
    	  String tmp = SysPropertyUtil.getByAlias("admin.account", "admin");
          return tmp.equals(user.getAccount());
    }
    
    public static boolean currentUserIsAdmin() {
    	User user = getCurrentUser();
    	
  	  	String tmp = SysPropertyUtil.getByAlias("admin.account", "admin");
        return tmp.equals(user.getAccount());
  }
}
