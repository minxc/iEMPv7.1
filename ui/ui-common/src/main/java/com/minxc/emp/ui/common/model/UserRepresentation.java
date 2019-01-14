package com.minxc.emp.ui.common.model;

import java.util.ArrayList;
import java.util.List;

import org.minxc.emp.idm.api.model.User;


public class UserRepresentation extends AbstractRepresentation {

    protected String id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String fullName;
    protected List<GroupRepresentation> groups = new ArrayList<>();
    protected List<String> privileges = new ArrayList<>();

    public UserRepresentation() {

    }

    public UserRepresentation(User user) {
        setId(user.getUserId());
        setFirstName("");
        setLastName("");
        setFullName((user.getFullname()));
        setEmail(user.getEmail());
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<GroupRepresentation> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupRepresentation> groups) {
        this.groups = groups;
    }

    public List<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<String> privileges) {
        this.privileges = privileges;
    }

}
