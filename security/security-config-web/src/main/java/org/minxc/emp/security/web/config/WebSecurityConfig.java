package org.minxc.emp.security.web.config;

import org.springframework.context.annotation.Configuration;
import org.minxc.emp.security.authentication.AccessDecisionManagerImpl;
import org.minxc.emp.security.authentication.FilterInvocationSecurityMetadataSourceImpl;
import org.minxc.emp.security.filter.SecurityRequestCsrfMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.PortMapper;
import org.springframework.security.web.PortMapperImpl;
import org.springframework.security.web.PortResolver;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.channel.ChannelDecisionManager;
import org.springframework.security.web.access.channel.ChannelDecisionManagerImpl;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.access.channel.ChannelProcessor;
import org.springframework.security.web.access.channel.InsecureChannelProcessor;
import org.springframework.security.web.access.channel.RetryWithHttpEntryPoint;
import org.springframework.security.web.access.channel.RetryWithHttpsEntryPoint;
import org.springframework.security.web.access.channel.SecureChannelProcessor;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
/**

* @Title: WebSecurityConfiguration 

* @Package: org.minxc.emp.web.security.config 

* @Description:  web系统安全配置类

* @author: Xianchang.min  

* @date  2018/8/17 0:40 

* @version V1.0  

*/


@Configuration
public class WebSecurityConfig {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // @Bean
    // public JdbcTokenRepositoryImpl tokenRepository(){
    // JdbcTokenRepositoryImpl jtr = new JdbcTokenRepositoryImpl();
    // jtr.setDataSource(dataSource);
    // return jtr;
    // }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        return new AccessDecisionManagerImpl();
    }

    @Bean(name = "securityMetadataSource")
    public FilterInvocationSecurityMetadataSource securityMetadataSource() {
        FilterInvocationSecurityMetadataSourceImpl securityMetaDataSource = new FilterInvocationSecurityMetadataSourceImpl();

        //设置不需要过滤的url地址
        List<String> ingnores = new ArrayList<>(8);
//        ingnores.add("/login.*");
//        ingnores.add("/index");
        ingnores.add("/hplus/*");
        ingnores.add("/favicon.ico");
        ingnores.add("/");
        securityMetaDataSource.setIngores(ingnores);
        return securityMetaDataSource;
    }
    
    
    @SuppressWarnings("unused")
	private SecurityRequestCsrfMatcher csrfSecurityRequestMatcher() { // 加入需要排除阻止CSRF攻击的链表链接，链接地址中包含/rest字符串的，对其忽略CSRF保护策略
        SecurityRequestCsrfMatcher csrfSecurityRequestMatcher = new SecurityRequestCsrfMatcher();
        List<String> list = new ArrayList<String>();
        list.add("/rest/");
        csrfSecurityRequestMatcher.setIngores(list);
        return csrfSecurityRequestMatcher;
    }

    @Bean(name = "springSecurityFilterChain")
    public FilterChainProxy springSecurityFilterChain() {
    	
        List<SecurityFilterChain> filterChains = new ArrayList<>();
//        List<Filter> resourceFilters = new ArrayList<>();
//        resourceFilters.add(channelProcessingFilter());
//        DefaultSecurityFilterChain resourcesChains = buildSecurityFilterChain("/resources/**", resourceFilters); // /resources/**
//        
//        // 路径
//        DefaultSecurityFilterChain loginPageChains = buildSecurityFilterChain("/index", resourceFilters); // /login 路径
//        DefaultSecurityFilterChain errorChains = buildSecurityFilterChain("/error", resourceFilters); // /error 路径
//        DefaultSecurityFilterChain rootChains = buildSecurityFilterChain("/", resourceFilters); // // 路径
        
        List<Filter> anyFilters = new ArrayList<>();
//        anyFilters.add(channelProcessingFilter());   //TODO:暂时注释掉channelProcessingFilter
        anyFilters.add(securityContextPersistenceFilter());  // 2
//        anyFilters.add(concurrentSessionFilter());
        anyFilters.add(logoutFilter());    // 设置登出过滤器
        anyFilters.add(usernamePasswordAuthenticationFilter());
        anyFilters.add(requestCacheAwareFilter());  
        anyFilters.add(securityContextHolderAwareRequestFilter());  
        anyFilters.add(anonymousAuthenticationFilter());  
        anyFilters.add(rememberMeAuthenticationFilter());
        anyFilters.add(exceptionTranslationFilter());
        anyFilters.add(sessionManagementFilter());
        anyFilters.add(filterSecurityInterceptor());
        DefaultSecurityFilterChain anyChains = buildSecurityFilterChain("/**", anyFilters);
//        filterChains.add(resourcesChains);
//        filterChains.add(loginPageChains);
//        filterChains.add(errorChains);
//        filterChains.add(rootChains);
        filterChains.add(anyChains);
        FilterChainProxy filterChainProxy = new FilterChainProxy(filterChains);
        return filterChainProxy;
    }
    
    

    private DefaultSecurityFilterChain buildSecurityFilterChain(String pathMatcherPattern, List<Filter> filters) {
        RequestMatcher requestMatcher = new AntPathRequestMatcher(pathMatcherPattern);
        DefaultSecurityFilterChain chainOne = new DefaultSecurityFilterChain(requestMatcher, filters);
        return chainOne;
    }

    @Bean
    public ChannelProcessingFilter channelProcessingFilter() {
        ChannelProcessingFilter channelProcessingFilter = new ChannelProcessingFilter();
        channelProcessingFilter.setChannelDecisionManager(channelDecisionManager());
        channelProcessingFilter.setSecurityMetadataSource(securityMetadataSource());
        return channelProcessingFilter;
    }

    @Bean
    public ChannelDecisionManager channelDecisionManager() {
        ChannelDecisionManagerImpl channelDecisionManager = new ChannelDecisionManagerImpl();
        List<ChannelProcessor> processors = new ArrayList<>();
        processors.add(insecureChannelProcessor());
        processors.add(secureChannelProcessor());
        channelDecisionManager.setChannelProcessors(processors);
        return channelDecisionManager;
    }

    @Bean
    public InsecureChannelProcessor insecureChannelProcessor() {
        InsecureChannelProcessor insecureChannelProcessor = new InsecureChannelProcessor();
        insecureChannelProcessor.setEntryPoint(retryWithHttpEntryPoint());
        return insecureChannelProcessor;
    }

    @Bean
    public SecureChannelProcessor secureChannelProcessor() {
        SecureChannelProcessor secureChannelProcessor = new SecureChannelProcessor();
        secureChannelProcessor.setEntryPoint(retryWithHttpsEntryPoint());
        return secureChannelProcessor;
    }

    @Bean
    public RetryWithHttpEntryPoint retryWithHttpEntryPoint() {
        RetryWithHttpEntryPoint entryPoint = new RetryWithHttpEntryPoint();
        entryPoint.setPortMapper(portMapper());
        entryPoint.setPortResolver(portResolver());
        return entryPoint;
    }

    @Bean
    public RetryWithHttpsEntryPoint retryWithHttpsEntryPoint() {
        RetryWithHttpsEntryPoint entryPoint = new RetryWithHttpsEntryPoint();
        entryPoint.setPortMapper(portMapper());
        entryPoint.setPortResolver(portResolver());
        return entryPoint;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public ConcurrentSessionFilter concurrentSessionFilter() {
        ConcurrentSessionFilter concurrentSessionFilter = new ConcurrentSessionFilter(sessionRegistry());
        return concurrentSessionFilter;
    }

    /**
     * securityContext拦截
     *
     * @return
     */

    @Bean
    public SecurityContextPersistenceFilter securityContextPersistenceFilter() {
        SecurityContextPersistenceFilter securityContextPersistenceFilter = new SecurityContextPersistenceFilter(
                httpSessionSecurityContextRepository());
        securityContextPersistenceFilter.setBeanName("securityContextPersistenceFilter");
        securityContextPersistenceFilter.setForceEagerSessionCreation(false);  //设置是否生成session
        return securityContextPersistenceFilter;

    }

    @Bean(name = "httpSessionSecurityContextRepository")
    public HttpSessionSecurityContextRepository httpSessionSecurityContextRepository() {
        HttpSessionSecurityContextRepository httpSessionSecurityContextRepository = new HttpSessionSecurityContextRepository();
        httpSessionSecurityContextRepository.setAllowSessionCreation(true);
        httpSessionSecurityContextRepository
                .setSpringSecurityContextKey(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        httpSessionSecurityContextRepository.setDisableUrlRewriting(false);  //设置允许;jsessionid=xxxxx在url上面，新版Spring security不允许在url中出现;因此启用url重写，false时启用url重写ex：http://127.0.0.1:8080/login;jsessionid=889595CDB56828C5CB3FABCF25D7F681?para=errorauth；true时不会重写数据
        return httpSessionSecurityContextRepository;
    }

    @Bean(name = "portMapper")
    public PortMapper portMapper() {
        PortMapperImpl portMapper = new PortMapperImpl();
        return portMapper;
    }

    @Bean(name = "portResolver")
    public PortResolver portResolver() {
        PortResolverImpl portResolver = new PortResolverImpl();
        portResolver.setPortMapper(portMapper());
        return portResolver;
    }

    /**
     * usernamePassword授权拦截     请求方式："/login", "POST"
     */
    @Bean(name = "usernamePasswordAuthenticationFilter")
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() {
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
        usernamePasswordAuthenticationFilter
                .setUsernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY);
        usernamePasswordAuthenticationFilter
                .setPasswordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY);
        usernamePasswordAuthenticationFilter.setAllowSessionCreation(true);
        usernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        usernamePasswordAuthenticationFilter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy());
        usernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        usernamePasswordAuthenticationFilter.setRememberMeServices(rememberMeServices());
        usernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
        return usernamePasswordAuthenticationFilter;

    }

    @Bean(name = "authenticationManager")
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> authenticationProviders = new ArrayList<>();
        authenticationProviders.add(authenticationProvider());
        authenticationProviders.add(rememberMeAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(authenticationProviders);
        return authenticationManager;
    }

    @Bean(name = "rememberMeAuthenticationProvider")
    public RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
        RememberMeAuthenticationProvider rememberMeAuthenticationProvider = new RememberMeAuthenticationProvider(
                AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY); // key
        return rememberMeAuthenticationProvider;
    }

    @Bean(name = "authenticationSuccessHandler")
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler = new SimpleUrlAuthenticationSuccessHandler();
        authenticationSuccessHandler.setDefaultTargetUrl("/home");
        return authenticationSuccessHandler;
    }

    @Bean(name = "authenticationFailureHandler")
    public AuthenticationFailureHandler authenticationFailureHandler() {
        SimpleUrlAuthenticationFailureHandler authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();
        authenticationFailureHandler.setDefaultFailureUrl("/index");
        return authenticationFailureHandler;
    }

    // 注销过滤器    默认处理url为  /logout
    @Bean(name = "logoutFilter")
    public LogoutFilter logoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter("/", logoutHandler()); // 退出成功跳转页面 /login   
        return logoutFilter;
    }

    // 注销监听器
    @Bean(name = "logoutHandler")
    public SecurityContextLogoutHandler logoutHandler() {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler(); // 退出成功跳转页面
        return logoutHandler;
    }

    @Bean(name = "rememberMeServices")
    public TokenBasedRememberMeServices rememberMeServices() {
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(
                AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, userDetailsService);
        return rememberMeServices;

    }

    @Bean(name = "sessionAuthenticationStrategy")
    public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        ConcurrentSessionControlAuthenticationStrategy sessionAuthenticationStrategy = new ConcurrentSessionControlAuthenticationStrategy(
                sessionRegistry());
        sessionAuthenticationStrategy.setExceptionIfMaximumExceeded(true);
        sessionAuthenticationStrategy.setMaximumSessions(1);
        return sessionAuthenticationStrategy;

    }

    @Bean(name = "sessionRegistry")
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;

    }

    /***
     * 7记住密码功能(COOKIE方式)
     */
    @Bean
    public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() {
        RememberMeAuthenticationFilter rememberMeAuthenticationFilter = new RememberMeAuthenticationFilter(
                authenticationManager(), rememberMeServices());
        rememberMeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        return rememberMeAuthenticationFilter;
    }

    /**
     * <!-- 9异常处理过滤器 -->
     */
    @Bean
    public ExceptionTranslationFilter exceptionTranslationFilter() {
        ExceptionTranslationFilter exceptionTranslationFilter = new ExceptionTranslationFilter(
                authenticationEntryPoint());
        exceptionTranslationFilter.setAccessDeniedHandler(accessDeniedHandler());
        return exceptionTranslationFilter;
    }

    
    
    //登录过滤处理器
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        LoginUrlAuthenticationEntryPoint authenticationEntryPoint = new LoginUrlAuthenticationEntryPoint(
                "/index");
        authenticationEntryPoint.setUseForward(false);
        authenticationEntryPoint.setForceHttps(false);
        return authenticationEntryPoint;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
        accessDeniedHandler.setErrorPage("/403");
        return accessDeniedHandler;
    }

    /***
     *
     * 核心过滤器
     *
     */
    @Bean
    public FilterSecurityInterceptor filterSecurityInterceptor() {
        FilterSecurityInterceptor interceptor = new FilterSecurityInterceptor();
        interceptor.setAccessDecisionManager(accessDecisionManager());
        interceptor.setAuthenticationManager(authenticationManager());
        interceptor.setRejectPublicInvocations(false);
        interceptor.setSecurityMetadataSource(securityMetadataSource()); // 核心资源获取
        return interceptor;
    }

    /**
     * <!-- 页面标签权限功能依赖 --> <beans:bean id="webInvocationFilter" class=
     * "org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator">
     * <beans:constructor-arg ref="felicityFilterSecurityInterceptor" />
     * </beans:bean>
     */

    @Bean
    public WebInvocationPrivilegeEvaluator webInvocationPrivilegeEvaluator() {
        DefaultWebInvocationPrivilegeEvaluator webInvocationPrivilegeEvaluator = new DefaultWebInvocationPrivilegeEvaluator(
                filterSecurityInterceptor());
        return webInvocationPrivilegeEvaluator;
    }

//    ObjectPostProcessor objectPostProcessor(){
//        ObjectPostProcessor objectPostProcessor = new ObjectPostProcessor() {
//            @Override
//            public Object postProcess(Object object) {
//                return null;
//            }
//        }
//    }
    
    @Bean("SESSION_MANAGEMENT_FILTER")
    public SessionManagementFilter  sessionManagementFilter() {
    	SessionManagementFilter sessionFilter = new SessionManagementFilter(httpSessionSecurityContextRepository(), sessionAuthenticationStrategy());
    	return sessionFilter;
    }
    
    @Bean
    public RequestCacheAwareFilter  requestCacheAwareFilter() {
    	return new RequestCacheAwareFilter();
    }
    @Bean
    public SecurityContextHolderAwareRequestFilter  securityContextHolderAwareRequestFilter() {
    	return new SecurityContextHolderAwareRequestFilter();
    }
    @Bean
    public AnonymousAuthenticationFilter anonymousAuthenticationFilter() {
    	AnonymousAuthenticationFilter anonymousFilter = new AnonymousAuthenticationFilter("ANONYMOUS_FILTER");
    	return anonymousFilter;
    }
}
