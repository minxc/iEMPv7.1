package org.flowable.ui.common.filter;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.flowable.ui.common.properties.FlowableCommonAppProperties;
import org.flowable.ui.common.service.idm.RemoteIdmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

/**
 * @author Filip Hrisafov
 */
public class FlowableCookieFilterRegistrationBean extends FilterRegistrationBean {

    protected final RemoteIdmService remoteIdmService;

    protected final FlowableCommonAppProperties properties;

    protected FlowableCookieFilterCallback filterCallback;
    protected Collection<String> requiredPrivileges;

    public FlowableCookieFilterRegistrationBean(RemoteIdmService remoteIdmService, FlowableCommonAppProperties properties) {
        this.remoteIdmService = remoteIdmService;
        this.properties = properties;
    }

    @PostConstruct
    protected void initializeFilter() {
        if (getFilter() == null) {
            FlowableCookieFilter flowableCookieFilter = new FlowableCookieFilter(remoteIdmService, properties);
            flowableCookieFilter.setFilterCallback(filterCallback);
            flowableCookieFilter.setRequiredPrivileges(requiredPrivileges);
            flowableCookieFilter.initCaches();
            setFilter(flowableCookieFilter);
        }
    }

    @Autowired(required = false)
    public void setFilterCallback(FlowableCookieFilterCallback filterCallback) {
        this.filterCallback = filterCallback;
    }

    public void setRequiredPrivileges(Collection<String> requiredPrivileges) {
        this.requiredPrivileges = requiredPrivileges;
    }
}
