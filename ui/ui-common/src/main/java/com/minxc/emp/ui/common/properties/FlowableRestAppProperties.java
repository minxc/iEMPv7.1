package com.minxc.emp.ui.common.properties;

import java.util.Objects;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

/**
 * Common Rest properties for the UI Apps.
 *
 * @author Filip Hrisafov
 */
@ConfigurationProperties(prefix = "flowable.rest.app")
public class FlowableRestAppProperties {

    /**
     * Configures the way user credentials are verified when doing a REST API call:
     * 'any-user' : the user needs to exist and the password need to match. Any user is allowed to do the call (this is the pre 6.3.0 behavior)
     * 'verify-privilege' : the user needs to exist, the password needs to match and the user needs to have the 'rest-api' privilege
     * If nothing set, defaults to 'verify-privilege'
     */
    private String authenticationMode = "verify-privilege";

    public String getAuthenticationMode() {
        return authenticationMode;
    }

    public void setAuthenticationMode(String authenticationMode) {
        this.authenticationMode = authenticationMode;
    }

    public boolean isVerifyRestApiPrivilege() {
        if (StringUtils.hasText(authenticationMode)) {
            return Objects.equals("verify-privilege", authenticationMode);
        }

        return true;
    }
}
