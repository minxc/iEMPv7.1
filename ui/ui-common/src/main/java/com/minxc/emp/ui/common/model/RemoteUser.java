package com.minxc.emp.ui.common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.minxc.emp.idm.api.model.User;

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
    public String getMobile() {
        return null;
    }

    @Override
    public void setAttributes(Map<String, String> map) {

    }

    @Override
    public Map<String, String> getAttributes() {
        return null;
    }

    @Override
    public String getAttrbuite(String key) {
        return null;
    }

    @Override
    public Integer getStatus() {
        return null;
    }

    @Override
    public String getWeixin() {
        return null;
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
    public String getUserId() {
        return null;
    }

    @Override
    public void setUserId(String userId) {

    }

    @Override
    public String getFullname() {
        return null;
    }

    @Override
    public void setFullname(String fullName) {

    }

    @Override
    public String getAccount() {
        return null;
    }

    @Override
    public void setAccount(String account) {

    }

    @Override
    public String getPassword() {
        // Not supported
        return null;
    }

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

    @Override
    public String getIdentityType() {
        return null;
    }
}
