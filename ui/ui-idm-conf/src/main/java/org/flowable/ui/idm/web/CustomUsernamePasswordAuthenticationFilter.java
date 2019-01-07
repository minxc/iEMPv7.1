package org.flowable.ui.idm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUsernamePasswordAuthenticationFilter.class);

    public CustomUsernamePasswordAuthenticationFilter() {
        super();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            LOGGER.error("Error setting character encoding to UTF-8");
        }
        return super.attemptAuthentication(request, response);
    }
}
