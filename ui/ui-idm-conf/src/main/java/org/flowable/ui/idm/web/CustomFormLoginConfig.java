package org.flowable.ui.idm.web;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * Custom implementation of {@link FormLoginConfigurer}, to have control over the auth filter instance.
 */
public final class CustomFormLoginConfig<H extends HttpSecurityBuilder<H>> extends AbstractAuthenticationFilterConfigurer<H, CustomFormLoginConfig<H>, CustomUsernamePasswordAuthenticationFilter> {

    public CustomFormLoginConfig() {
        super(new CustomUsernamePasswordAuthenticationFilter(), null);
        getAuthenticationFilter().setPasswordParameter("username");
        getAuthenticationFilter().setPasswordParameter("password");
    }

    public CustomFormLoginConfig<H> usernameParameter(String usernameParameter) {
        getAuthenticationFilter().setUsernameParameter(usernameParameter);
        return this;
    }

    public CustomFormLoginConfig<H> passwordParameter(String passwordParameter) {
        getAuthenticationFilter().setPasswordParameter(passwordParameter);
        return this;
    }

    @Override
    public void init(H http) throws Exception {
        super.init(http);
        initDefaultLoginFilter(http);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer#createLoginProcessingUrlMatcher(java.lang.String)
     */
    @Override
    protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
        return new CustomAntPathMatcher(loginProcessingUrl, "POST");
    }

    /**
     * If available, initializes the {@link DefaultLoginPageGeneratingFilter} shared object.
     *
     * @param http
     *            the {@link HttpSecurityBuilder} to use
     */
    private void initDefaultLoginFilter(H http) {
        DefaultLoginPageGeneratingFilter loginPageGeneratingFilter = http.getSharedObject(DefaultLoginPageGeneratingFilter.class);
        if (loginPageGeneratingFilter != null && !isCustomLoginPage()) {
            loginPageGeneratingFilter.setFormLoginEnabled(true);
            loginPageGeneratingFilter.setUsernameParameter(getAuthenticationFilter().getUsernameParameter());
            loginPageGeneratingFilter.setPasswordParameter(getAuthenticationFilter().getPasswordParameter());
            loginPageGeneratingFilter.setLoginPageUrl(getLoginPage());
            loginPageGeneratingFilter.setFailureUrl(getFailureUrl());
            loginPageGeneratingFilter.setAuthenticationUrl(getLoginProcessingUrl());
        }
    }
}
