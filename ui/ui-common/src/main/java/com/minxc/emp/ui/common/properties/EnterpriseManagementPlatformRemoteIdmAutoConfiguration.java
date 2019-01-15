package com.minxc.emp.ui.common.properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.core.GrantedAuthorityDefaults;

/**
 * @author Filip Hrisafov
 */
@EnableConfigurationProperties({
    EnterpriseManagementPlatformCommonAppProperties.class,
    EnterpriseManagementPlatformRestAppProperties.class
})
@Configuration
public class EnterpriseManagementPlatformRemoteIdmAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults(EnterpriseManagementPlatformCommonAppProperties commonAppProperties) {
        return new GrantedAuthorityDefaults(commonAppProperties.getRolePrefix());
    }
}
