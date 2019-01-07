package org.flowable.ui.idm.model;

import org.flowable.ui.common.model.AbstractRepresentation;

/**
 * @author Joram Barrez
 */
public class AddGroupPrivilegeRepresentation extends AbstractRepresentation {

    protected String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

}
