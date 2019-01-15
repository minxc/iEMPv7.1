package com.minxc.emp.ui.common.tenant;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import com.minxc.emp.ui.common.properties.EnterpriseManagementPlatformCommonAppProperties;
import com.minxc.emp.ui.common.security.EMPAppUser;
import com.minxc.emp.ui.common.security.SecurityUtils;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class DefaultTenantProvider implements TenantProvider {
    
//    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTenantProvider.class);

    private String tenantId;
    
    public DefaultTenantProvider(EnterpriseManagementPlatformCommonAppProperties commonAppProperties) {
        super();
        String configuredTenantId = commonAppProperties.getTenantId();
        if(! StringUtils.isBlank(configuredTenantId)) {
            // trim whitespace as trailing whitespace are possible in properties files and easy to do
            configuredTenantId = configuredTenantId.trim();
            
            // quotes can help solve whitespace issues
            log.debug("Found configured tenantId: '{}'", configuredTenantId);
            
            this.tenantId = configuredTenantId;
        }
    }

    @Override
    public String getTenantId() {
        if(tenantId != null) {
            log.debug("Using configured tenantId: '{}'", tenantId);
            return tenantId;
        }
        
        EMPAppUser appUser = SecurityUtils.getCurrentFlowableAppUser();
        if(appUser != null) {
            // quotes can help solve whitespace issues, trimming here would not 
            // help solve the problem at source which is in user database
            log.debug("Using user tenantId: '{}'", tenantId);
            
            return appUser.getUserObject().getTenantId();
        }

        log.debug("No tenantId");

        return null;
    }
    
}
