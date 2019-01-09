package com.minxc.emp.ui.idm.model;

import com.minxc.emp.ui.common.model.AbstractRepresentation;

/**
 * @author Joram Barrez
 */
public class AddUserPrivilegeRepresentation extends AbstractRepresentation {

    protected String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
