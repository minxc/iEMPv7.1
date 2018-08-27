package org.minxc.emp.model;

public class SystemLogErrorEntityWithBLOBs extends SystemLogErrorEntity {
    private String content;

    private String stackTrace;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace == null ? null : stackTrace.trim();
    }
}