package com.minxc.emp.ui.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minxc.emp.ui.common.model.RemoteToken;

public interface EnterpriseManagementPlatformCookieFilterCallback {

    void onValidTokenFound(HttpServletRequest request, HttpServletResponse response, RemoteToken token);

    void onFilterCleanup(HttpServletRequest request, HttpServletResponse response);

}
