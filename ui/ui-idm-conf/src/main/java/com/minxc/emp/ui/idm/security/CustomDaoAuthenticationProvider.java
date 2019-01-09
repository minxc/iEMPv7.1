package com.minxc.emp.ui.idm.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

/**
 * @author jbarrez
 */
public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {

    protected void additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails userDetails,
            org.springframework.security.authentication.UsernamePasswordAuthenticationToken authentication) throws org.springframework.security.core.AuthenticationException {

        // Overriding this method to catch empty/null passwords. This happens when users are synced with LDAP sync:
        // they will have an external id, but no password (password is checked against ldap).
        //
        // The default DaoAuthenticationProvider will choke on an empty password (an arrayIndexOutOfBoundsException
        // somewhere deep in the bowels of password encryption), hence this override
        if (StringUtils.isEmpty(userDetails.getPassword())) {
            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }

        super.additionalAuthenticationChecks(userDetails, authentication);

    }

}
