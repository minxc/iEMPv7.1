package com.minxc.emp.ui.common.model;

import org.flowable.idm.api.Group;

public class RemoteGroup implements Group {

    protected String id;
    protected String name;

    public RemoteGroup() {

    }

    public RemoteGroup(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        // not supported
        return null;
    }

    @Override
    public void setType(String string) {
        // not supported
    }

}
