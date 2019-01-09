package com.minxc.emp.ui.idm.model;

import com.minxc.emp.ui.common.model.UserRepresentation;

/**
 * @author Joram Barrez
 */
public class CreateUserRepresentation extends UserRepresentation {

    protected String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
