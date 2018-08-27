package org.minxc.emp.core.api.response.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.minxc.emp.core.api.response.RequestResult;
import org.minxc.emp.core.api.status.CommonStatusCode;
import org.minxc.emp.core.api.status.DefaultStatusCode;
import org.minxc.emp.core.api.status.StatusCode;

@Slf4j
@Setter
@Getter
@NoArgsConstructor
public class BaseResult implements RequestResult {

    private boolean isSuccess;
    private String code;
    private String message;
    private String cause;
    private StatusCode statusCode;

    public BaseResult(String errorMsg) {
        this.message = errorMsg;
        this.isSuccess = false;
    }
}
