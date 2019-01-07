package org.flowable.ui.idm.model;

import java.util.ArrayList;
import java.util.List;

import org.flowable.ui.common.model.AbstractRepresentation;
import org.flowable.ui.common.model.GroupRepresentation;
import org.flowable.ui.common.model.UserRepresentation;

/**
 * @author Joram Barrez
 */
public class PrivilegeRepresentation extends AbstractRepresentation {

    protected String id;
    protected String name;
    protected List<UserRepresentation> users;
    protected List<GroupRepresentation> groups;

    public PrivilegeRepresentation() {

    }

    public PrivilegeRepresentation(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserRepresentation> getUsers() {
        return users;
    }

    public void setUsers(List<UserRepresentation> users) {
        this.users = users;
    }

    public void addUser(UserRepresentation userRepresentation) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(userRepresentation);
    }

    public List<GroupRepresentation> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupRepresentation> groups) {
        this.groups = groups;
    }

    public void addGroup(GroupRepresentation groupRepresentation) {
        if (groups == null) {
            groups = new ArrayList<>();
        }
        groups.add(groupRepresentation);
    }

}
