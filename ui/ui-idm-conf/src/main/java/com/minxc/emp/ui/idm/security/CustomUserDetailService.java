package com.minxc.emp.ui.idm.security;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Introduced new interface for our {@link UserDetailsService}, to fool the Spring proxy stuff, so we can inject it into the {@link CustomPersistentRememberMeServices}.
 * 
 * @author Joram Barrez
 */
public interface CustomUserDetailService {

    UserDetails loadByUserId(final String userId);

    UserDetails loadUserByUsername(final String username);

}
