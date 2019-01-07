package org.flowable.ui.common.model;

import java.util.ArrayList;
import java.util.List;

import org.flowable.idm.api.User;

public class RemoteUser implements User {

    protected String id;
    protected String firstName;
    protected String lastName;
    protected String displayName;
    protected String email;
    protected String fullName;
    protected String tenantId;
    protected List<RemoteGroup> groups = new ArrayList<>();
    protected List<String> privileges = new ArrayList<>();

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<RemoteGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<RemoteGroup> groups) {
        this.groups = groups;
    }

    public List<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<String> privileges) {
        this.privileges = privileges;
    }

    @Override
    public String getPassword() {
        // Not supported
        return null;
    }

    @Override
    public void setPassword(String string) {
        // Not supported
    }

    @Override
    public boolean isPictureSet() {
        // Not supported
        return false;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
