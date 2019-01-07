package org.flowable.ui.common.service.exception;

/**
 * Exception thrown when an operation is performed for which the current user has insufficient permissions.
 * 
 * @author Frederik Heremans
 */
public class NotPermittedException extends BaseModelerRestException {

    private static final long serialVersionUID = 1L;

    public NotPermittedException() {
    }

    public NotPermittedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotPermittedException(String message) {
        super(message);
    }

    public NotPermittedException(Throwable cause) {
        super(cause);
    }
}
