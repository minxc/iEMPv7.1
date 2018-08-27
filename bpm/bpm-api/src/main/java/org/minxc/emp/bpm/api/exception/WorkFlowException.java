package org.minxc.emp.bpm.api.exception;

import org.minxc.emp.base.api.constant.StatusCode;
import org.minxc.emp.base.api.exception.BusinessException;

public class WorkFlowException extends BusinessException {

    public WorkFlowException(String msg, StatusCode errorCode) {
        super(msg, errorCode);
    }

    public WorkFlowException(StatusCode errorCode) {
        super(errorCode);
    }

    public WorkFlowException(StatusCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }


}
