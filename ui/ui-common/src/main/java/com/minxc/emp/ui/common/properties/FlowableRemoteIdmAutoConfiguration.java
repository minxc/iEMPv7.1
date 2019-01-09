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
    FlowableCommonAppProperties.class,
    FlowableRestAppProperties.class
})
@Configuration
public class FlowableRemoteIdmAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults(FlowableCommonAppProperties commonAppProperties) {
        return new GrantedAuthorityDefaults(commonAppProperties.getRolePrefix());
    }
}
