package com.minxc.emp.ui.common.model;

import org.minxc.emp.idm.api.model.Group;

/**
 * @author Joram Barrez
 */
public class GroupRepresentation extends AbstractRepresentation {

    protected String id;
    protected String name;
    protected String type;

    public GroupRepresentation() {
    }

    public GroupRepresentation(Group group) {
        setId(group.getGroupId());
        setName(group.getName());
        setType(group.getGroupType());
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
