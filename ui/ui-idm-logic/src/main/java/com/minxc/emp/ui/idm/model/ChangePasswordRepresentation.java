package com.minxc.emp.ui.idm.model;

import com.minxc.emp.ui.common.model.AbstractRepresentation;

public class ChangePasswordRepresentation extends AbstractRepresentation {

    protected String originalPassword;
    protected String newPassword;

    public String getOriginalPassword() {
        return originalPassword;
    }

    public void setOriginalPassword(String originalPassword) {
        this.originalPassword = originalPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
