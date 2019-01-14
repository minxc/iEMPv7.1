package com.minxc.emp.ui.common.model;

import org.minxc.emp.idm.api.model.Group;
import org.minxc.emp.idm.api.model.GroupStructEnum;


public class RemoteGroup implements Group {

    protected String id;
    protected String name;

    public RemoteGroup() {
    }

    public RemoteGroup(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getGroupId() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGroupCode() {
        return null;
    }

    @Override
    public Long getSeq() {
        return null;
    }

    @Override
    public String getGroupType() {
        return null;
    }

    @Override
    public GroupStructEnum getStruct() {
        return null;
    }

    @Override
    public String getParentId() {
        return null;
    }

    @Override
    public String getPath() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        // not supported
        return null;
    }

    public void setType(String string) {
        // not supported
    }

    @Override
    public String getIdentityType() {
        return null;
    }
}
