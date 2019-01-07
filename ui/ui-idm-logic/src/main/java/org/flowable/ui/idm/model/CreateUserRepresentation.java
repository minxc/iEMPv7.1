package org.flowable.ui.idm.model;

import org.flowable.ui.common.model.UserRepresentation;

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
