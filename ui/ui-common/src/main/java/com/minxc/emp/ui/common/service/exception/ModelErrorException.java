package com.minxc.emp.ui.common.service.exception;

/**
 * @author Tijs Rademakers
 */
public class ModelErrorException extends BaseModelerRestException {

    private static final long serialVersionUID = 1L;

    public ModelErrorException() {
    }

    public ModelErrorException(String message) {
        super(message);
    }

    public ModelErrorException(String message, Throwable t) {
        super(message, t);
    }
}
