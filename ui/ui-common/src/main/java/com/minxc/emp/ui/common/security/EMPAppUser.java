package com.minxc.emp.ui.common.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * A {@link UserDetails} implementation that exposes the {@link org.minxc.emp.idm.api.model.User} object the logged in user represents.
 * 
 * @author Frederik Heremans
 * @author Joram Barrez
 */
public class EMPAppUser extends User {

    private static final long serialVersionUID = 1L;

    protected org.minxc.emp.idm.api.model.User userObject;

    /**
     * The userId needs to be passed explicitly. It can be the email, but also the external id when eg LDAP is being used.
     */
    public EMPAppUser(org.minxc.emp.idm.api.model.User user, String userId, Collection<? extends GrantedAuthority> authorities) {
        super(userId, user.getPassword() != null ? user.getPassword() : "", authorities); // password needs to be non null
        this.userObject = user;
    }

    public org.minxc.emp.idm.api.model.User getUserObject() {
        return userObject;
    }
}
