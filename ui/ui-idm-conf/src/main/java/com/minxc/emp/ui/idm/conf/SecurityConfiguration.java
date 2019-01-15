package com.minxc.emp.ui.idm.conf;

import com.minxc.emp.ui.common.properties.EnterpriseManagementPlatformRestAppProperties;
import com.minxc.emp.ui.common.security.ActuatorRequestMatcher;
import com.minxc.emp.ui.common.security.ClearFlowableCookieLogoutHandler;
import com.minxc.emp.ui.common.security.DefaultPrivileges;
import com.minxc.emp.ui.idm.properties.EnterpriseManagementPlatformIdmAppProperties;
import com.minxc.emp.ui.idm.security.AjaxAuthenticationFailureHandler;
import com.minxc.emp.ui.idm.security.AjaxAuthenticationSuccessHandler;
import com.minxc.emp.ui.idm.security.AjaxLogoutSuccessHandler;
import com.minxc.emp.ui.idm.security.CustomDaoAuthenticationProvider;
import com.minxc.emp.ui.idm.security.CustomLdapAuthenticationProvider;
import com.minxc.emp.ui.idm.security.CustomPersistentRememberMeServices;
import com.minxc.emp.ui.idm.security.Http401UnauthorizedEntryPoint;
import com.minxc.emp.ui.idm.web.CustomFormLoginConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;

/**
 * Based on http://docs.spring.io/spring-security/site/docs/3.2.x/reference/htmlsingle/#multiple-httpsecurity
 *
 * @author Joram Barrez
 * @author Tijs Rademakers
 * @author Filip Hrisafov
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    //
    // GLOBAL CONFIG
    //

    @Autowired
    protected IdmIdentityService identityService;
    
    @Autowired
    protected EnterpriseManagementPlatformIdmAppProperties idmAppProperties;
    
    @Bean
    public UserDetailsService userDetailsService() {
        com.minxc.emp.ui.idm.security.UserDetailsService userDetailsService = new com.minxc.emp.ui.idm.security.UserDetailsService();
        userDetailsService.setUserValidityPeriod(idmAppProperties.getSecurity().getUserValidityPeriod());
        return userDetailsService;
    }

    @Bean(name = "dbAuthenticationProvider")
    @ConditionalOnMissingBean(AuthenticationProvider.class)
    @ConditionalOnProperty(prefix = "flowable.idm.ldap", name = "enabled", havingValue = "false", matchIfMissing = true)
    public AuthenticationProvider dbAuthenticationProvider(PasswordEncoder passwordEncoder) {
        CustomDaoAuthenticationProvider daoAuthenticationProvider = new CustomDaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

    @Bean(name = "ldapAuthenticationProvider")
    @ConditionalOnProperty(prefix = "flowable.idm.ldap", name = "enabled", havingValue = "true")
    public AuthenticationProvider ldapAuthenticationProvider() {
        CustomLdapAuthenticationProvider ldapAuthenticationProvider = new CustomLdapAuthenticationProvider(
                userDetailsService(), identityService);
        return ldapAuthenticationProvider;
    }

    //
    // REGULAR WEBAP CONFIG
    //

    @Configuration
    @Order(10) // API config first (has Order(1))
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private EnterpriseManagementPlatformIdmAppProperties idmAppProperties;

        @Autowired
        private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

        @Autowired
        private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

        @Autowired
        private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

        @Autowired
        private Http401UnauthorizedEntryPoint authenticationEntryPoint;

        @Autowired
        private RememberMeServices rememberMeServices;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .rememberMe()
                    .rememberMeServices(rememberMeServices)
                    .key(idmAppProperties.getSecurity().getRememberMeKey())
                    .and()
                    .logout()
                    .logoutUrl("/app/logout")
                    .logoutSuccessHandler(ajaxLogoutSuccessHandler)
                    .addLogoutHandler(new ClearFlowableCookieLogoutHandler())
                    .permitAll()
                    .and()
                    .csrf()
                    .disable() // Disabled, cause enabling it will cause sessions
                    .headers()
                    .frameOptions()
                    .sameOrigin()
                    .addHeaderWriter(new XXssProtectionHeaderWriter())
                    .and()
                    .authorizeRequests()
                    .antMatchers("/*").permitAll()
                    .antMatchers("/app/rest/authenticate").permitAll()
                    .antMatchers("/app/**").hasAuthority(DefaultPrivileges.ACCESS_IDM);

            // Custom login form configurer to allow for non-standard HTTP-methods (eg. LOCK)
            CustomFormLoginConfig<HttpSecurity> loginConfig = new CustomFormLoginConfig<>();
            loginConfig.loginProcessingUrl("/app/authentication")
                    .successHandler(ajaxAuthenticationSuccessHandler)
                    .failureHandler(ajaxAuthenticationFailureHandler)
                    .usernameParameter("j_username")
                    .passwordParameter("j_password")
                    .permitAll();

            http.apply(loginConfig);
        }

    }

    @Bean
    public CustomPersistentRememberMeServices rememberMeServices() {
        return new CustomPersistentRememberMeServices(idmAppProperties, userDetailsService());
    }

    //
    // BASIC AUTH
    //

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        protected final EnterpriseManagementPlatformRestAppProperties restAppProperties;
        protected final EnterpriseManagementPlatformIdmAppProperties idmAppProperties;

        public ApiWebSecurityConfigurationAdapter(EnterpriseManagementPlatformRestAppProperties restAppProperties,
                                                  EnterpriseManagementPlatformIdmAppProperties idmAppProperties) {
            this.restAppProperties = restAppProperties;
            this.idmAppProperties = idmAppProperties;
        }

        protected void configure(HttpSecurity http) throws Exception {

            http
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .csrf()
                    .disable();

            if (idmAppProperties.isRestEnabled()) {

                if (restAppProperties.isVerifyRestApiPrivilege()) {
                    http.antMatcher("/api/**").authorizeRequests().antMatchers("/api/**").hasAuthority(DefaultPrivileges.ACCESS_REST_API).and().httpBasic();
                } else {
                    http.antMatcher("/api/**").authorizeRequests().antMatchers("/api/**").authenticated().and().httpBasic();
                    
                }
                
            } else {
                http.antMatcher("/api/**").authorizeRequests().antMatchers("/api/**").denyAll();
                
            }
            
        }
    }

    //
    // Actuator
    //

    @ConditionalOnClass(EndpointRequest.class)
    @Configuration
    @Order(5) // Actuator configuration should kick in before the Form Login there should always be http basic for the endpoints
    public static class ActuatorWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable();

            http
                .requestMatcher(new ActuatorRequestMatcher())
                .authorizeRequests()
                .requestMatchers(EndpointRequest.to(InfoEndpoint.class, HealthEndpoint.class)).authenticated()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).hasAnyAuthority(DefaultPrivileges.ACCESS_ADMIN)
                .and().httpBasic();
        }
    }
}
