package org.minxc.emp.idm.api.model;

import java.util.Date;

public interface Token {

    String getId();

    String getTokenValue();

    void setTokenValue(String tokenValue);

    Date getTokenDate();

    void setTokenDate(Date tokenDate);

    String getIpAddress();

    void setIpAddress(String ipAddress);

    String getUserAgent();

    void setUserAgent(String userAgent);

    String getUserId();

    void setUserId(String userId);

    String getTokenData();

    void setTokenData(String tokenData);
}
