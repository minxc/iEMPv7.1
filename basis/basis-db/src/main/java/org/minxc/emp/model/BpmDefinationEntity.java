package org.minxc.emp.model;

import java.util.Date;

public class BpmDefinationEntity {
    private String id;

    private String name;

    private String key;

    private String description;

    private String typeId;

    private String status;

    private String actDefId;

    private String actModelId;

    private String actDeployId;

    private Long version;

    private String mainDefId;

    private String isMain;

    private String createBy;

    private Date createTime;

    private String createOrgId;

    private String updateBy;

    private Date updateTime;

    private Long supportMobile;

    private Long rev;

    private String defSetting;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getActDefId() {
        return actDefId;
    }

    public void setActDefId(String actDefId) {
        this.actDefId = actDefId == null ? null : actDefId.trim();
    }

    public String getActModelId() {
        return actModelId;
    }

    public void setActModelId(String actModelId) {
        this.actModelId = actModelId == null ? null : actModelId.trim();
    }

    public String getActDeployId() {
        return actDeployId;
    }

    public void setActDeployId(String actDeployId) {
        this.actDeployId = actDeployId == null ? null : actDeployId.trim();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getMainDefId() {
        return mainDefId;
    }

    public void setMainDefId(String mainDefId) {
        this.mainDefId = mainDefId == null ? null : mainDefId.trim();
    }

    public String getIsMain() {
        return isMain;
    }

    public void setIsMain(String isMain) {
        this.isMain = isMain == null ? null : isMain.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateOrgId() {
        return createOrgId;
    }

    public void setCreateOrgId(String createOrgId) {
        this.createOrgId = createOrgId == null ? null : createOrgId.trim();
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getSupportMobile() {
        return supportMobile;
    }

    public void setSupportMobile(Long supportMobile) {
        this.supportMobile = supportMobile;
    }

    public Long getRev() {
        return rev;
    }

    public void setRev(Long rev) {
        this.rev = rev;
    }

    public String getDefSetting() {
        return defSetting;
    }

    public void setDefSetting(String defSetting) {
        this.defSetting = defSetting == null ? null : defSetting.trim();
    }
}