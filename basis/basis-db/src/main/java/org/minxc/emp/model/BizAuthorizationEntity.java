package org.minxc.emp.model;

import java.util.Date;

public class BizAuthorizationEntity {
    private String rightsId;

    private String rightsObject;

    private String rightsTarget;

    private String rightsType;

    private String rightsIdentity;

    private String rightsIdentityName;

    private String rightsPermissionCode;

    private Date rightsCreateTime;

    private String rightsCreateBy;

    public String getRightsId() {
        return rightsId;
    }

    public void setRightsId(String rightsId) {
        this.rightsId = rightsId == null ? null : rightsId.trim();
    }

    public String getRightsObject() {
        return rightsObject;
    }

    public void setRightsObject(String rightsObject) {
        this.rightsObject = rightsObject == null ? null : rightsObject.trim();
    }

    public String getRightsTarget() {
        return rightsTarget;
    }

    public void setRightsTarget(String rightsTarget) {
        this.rightsTarget = rightsTarget == null ? null : rightsTarget.trim();
    }

    public String getRightsType() {
        return rightsType;
    }

    public void setRightsType(String rightsType) {
        this.rightsType = rightsType == null ? null : rightsType.trim();
    }

    public String getRightsIdentity() {
        return rightsIdentity;
    }

    public void setRightsIdentity(String rightsIdentity) {
        this.rightsIdentity = rightsIdentity == null ? null : rightsIdentity.trim();
    }

    public String getRightsIdentityName() {
        return rightsIdentityName;
    }

    public void setRightsIdentityName(String rightsIdentityName) {
        this.rightsIdentityName = rightsIdentityName == null ? null : rightsIdentityName.trim();
    }

    public String getRightsPermissionCode() {
        return rightsPermissionCode;
    }

    public void setRightsPermissionCode(String rightsPermissionCode) {
        this.rightsPermissionCode = rightsPermissionCode == null ? null : rightsPermissionCode.trim();
    }

    public Date getRightsCreateTime() {
        return rightsCreateTime;
    }

    public void setRightsCreateTime(Date rightsCreateTime) {
        this.rightsCreateTime = rightsCreateTime;
    }

    public String getRightsCreateBy() {
        return rightsCreateBy;
    }

    public void setRightsCreateBy(String rightsCreateBy) {
        this.rightsCreateBy = rightsCreateBy == null ? null : rightsCreateBy.trim();
    }
}