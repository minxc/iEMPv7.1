package com.minxc.emp.ui.common.filter;

import java.util.Collection;

import javax.annotation.PostConstruct;

import com.minxc.emp.ui.common.service.idm.RemoteIdmService;
import com.minxc.emp.ui.common.properties.EnterpriseManagementPlatformCommonAppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

/**
 * @author Filip Hrisafov
 */
public class EnterpriseManagementPlatformCookieFilterRegistrationBean extends FilterRegistrationBean {

    protected final RemoteIdmService remoteIdmService;

    protected final EnterpriseManagementPlatformCommonAppProperties properties;

    protected EnterpriseManagementPlatformCookieFilterCallback filterCallback;
    protected Collection<String> requiredPrivileges;

    public EnterpriseManagementPlatformCookieFilterRegistrationBean(RemoteIdmService remoteIdmService, EnterpriseManagementPlatformCommonAppProperties properties) {
        this.remoteIdmService = remoteIdmService;
        this.properties = properties;
    }

    @PostConstruct
    protected void initializeFilter() {
        if (getFilter() == null) {
            EnterpriseManagementPlatformCookieFilter flowableCookieFilter = new EnterpriseManagementPlatformCookieFilter(remoteIdmService, properties);
            flowableCookieFilter.setFilterCallback(filterCallback);
            flowableCookieFilter.setRequiredPrivileges(requiredPrivileges);
            flowableCookieFilter.initCaches();
            setFilter(flowableCookieFilter);
        }
    }

    @Autowired(required = false)
    public void setFilterCallback(EnterpriseManagementPlatformCookieFilterCallback filterCallback) {
        this.filterCallback = filterCallback;
    }

    public void setRequiredPrivileges(Collection<String> requiredPrivileges) {
        this.requiredPrivileges = requiredPrivileges;
    }
}
