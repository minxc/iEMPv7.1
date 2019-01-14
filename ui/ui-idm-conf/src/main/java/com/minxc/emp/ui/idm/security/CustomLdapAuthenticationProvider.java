package com.minxc.emp.ui.idm.security;

import org.flowable.idm.api.IdmIdentityService;
import com.minxc.emp.ui.common.security.EMPAppUser;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Tijs Rademakers
 */
public class CustomLdapAuthenticationProvider implements AuthenticationProvider {
    
    protected UserDetailsService userDetailsService;
    protected IdmIdentityService identityService;
    
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    protected GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    public CustomLdapAuthenticationProvider(UserDetailsService userDetailsService, IdmIdentityService identityService) {
        this.userDetailsService = userDetailsService;
        this.identityService = identityService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        
        boolean authenticated = identityService.checkPassword(authenticationToken.getName(), authenticationToken.getCredentials().toString());
        if (!authenticated) {
            throw new BadCredentialsException(messages.getMessage("LdapAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        
        EMPAppUser userDetails = (EMPAppUser) userDetailsService.loadUserByUsername(authenticationToken.getName());
        
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                userDetails, authenticationToken.getCredentials(), 
                authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));
        result.setDetails(authentication.getDetails());
        
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
