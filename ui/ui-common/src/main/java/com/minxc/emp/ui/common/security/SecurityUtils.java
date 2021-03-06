package com.minxc.emp.ui.common.security;

import org.minxc.emp.idm.api.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Utility class for Spring Security.
 */
public class SecurityUtils {

    private static User assumeUser;

    private SecurityUtils() {
    }

    /**
     * Get the login of the current user.
     */
    public static String getCurrentUserId() {
        User user = getCurrentUserObject();
        if (user != null) {
//            return user.getId();
            return user.getUserId();
        }
        return null;
    }

    /**
     * @return the {@link User} object associated with the current logged in user.
     */
    public static User getCurrentUserObject() {
        if (assumeUser != null) {
            return assumeUser;
        }

        User user = null;
        EMPAppUser appUser = getCurrentFlowableAppUser();
        if (appUser != null) {
            user = appUser.getUserObject();
        }
        return user;
    }

    public static EMPAppUser getCurrentFlowableAppUser() {
        EMPAppUser user = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null && securityContext.getAuthentication() != null) {
            Object principal = securityContext.getAuthentication().getPrincipal();
            if (principal instanceof EMPAppUser) {
                user = (EMPAppUser) principal;
            }
        }
        return user;
    }

    public static boolean currentUserHasCapability(String capability) {
        EMPAppUser user = getCurrentFlowableAppUser();
        for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
            if (capability.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }
        return false;
    }

    public static void assumeUser(User user) {
        assumeUser = user;
    }

    public static void clearAssumeUser() {
        assumeUser = null;
    }

}
