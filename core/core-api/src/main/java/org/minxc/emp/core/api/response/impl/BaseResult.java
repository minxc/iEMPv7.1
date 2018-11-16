package org.minxc.emp.core.api.response.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.minxc.emp.core.api.response.RequestResult;
import org.minxc.emp.core.api.status.StatusCode;

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
