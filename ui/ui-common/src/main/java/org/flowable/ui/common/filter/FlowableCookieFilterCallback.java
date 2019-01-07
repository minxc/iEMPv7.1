package org.flowable.ui.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.flowable.ui.common.model.RemoteToken;

public interface FlowableCookieFilterCallback {

    void onValidTokenFound(HttpServletRequest request, HttpServletResponse response, RemoteToken token);

    void onFilterCleanup(HttpServletRequest request, HttpServletResponse response);

}
