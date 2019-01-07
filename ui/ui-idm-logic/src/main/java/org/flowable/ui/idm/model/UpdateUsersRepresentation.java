package org.flowable.ui.idm.model;

import java.util.ArrayList;
import java.util.List;

import org.flowable.ui.common.model.AbstractRepresentation;

/**
 * @author Joram Barrez
 */
public class UpdateUsersRepresentation extends AbstractRepresentation {

    protected String id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected List<String> users = new ArrayList<>();

    public UpdateUsersRepresentation() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

}
