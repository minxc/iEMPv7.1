package org.minxc.emp.model;

import java.math.BigDecimal;
import java.util.Date;

public class BpmInstanceEntity {
    private String id;

    private String subject;

    private String defId;

    private String actDefId;

    private String defKey;

    private String defName;

    private String bizKey;

    private String status;

    private Date endTime;

    private BigDecimal duration;

    private String typeId;

    private String actInstId;

    private String createBy;

    private String creator;

    private Date createTime;

    private String createOrgId;

    private String updateBy;

    private Date updateTime;

    private String isFormmal;

    private String parentInstId;

    private Integer isForbidden;

    private String dataMode;

    private Long supportMobile;

    private String superNodeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getDefId() {
        return defId;
    }

    public void setDefId(String defId) {
        this.defId = defId == null ? null : defId.trim();
    }

    public String getActDefId() {
        return actDefId;
    }

    public void setActDefId(String actDefId) {
        this.actDefId = actDefId == null ? null : actDefId.trim();
    }

    public String getDefKey() {
        return defKey;
    }

    public void setDefKey(String defKey) {
        this.defKey = defKey == null ? null : defKey.trim();
    }

    public String getDefName() {
        return defName;
    }

    public void setDefName(String defName) {
        this.defName = defName == null ? null : defName.trim();
    }

    public String getBizKey() {
        return bizKey;
    }

    public void setBizKey(String bizKey) {
        this.bizKey = bizKey == null ? null : bizKey.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getActInstId() {
        return actInstId;
    }

    public void setActInstId(String actInstId) {
        this.actInstId = actInstId == null ? null : actInstId.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
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

    public String getIsFormmal() {
        return isFormmal;
    }

    public void setIsFormmal(String isFormmal) {
        this.isFormmal = isFormmal == null ? null : isFormmal.trim();
    }

    public String getParentInstId() {
        return parentInstId;
    }

    public void setParentInstId(String parentInstId) {
        this.parentInstId = parentInstId == null ? null : parentInstId.trim();
    }

    public Integer getIsForbidden() {
        return isForbidden;
    }

    public void setIsForbidden(Integer isForbidden) {
        this.isForbidden = isForbidden;
    }

    public String getDataMode() {
        return dataMode;
    }

    public void setDataMode(String dataMode) {
        this.dataMode = dataMode == null ? null : dataMode.trim();
    }

    public Long getSupportMobile() {
        return supportMobile;
    }

    public void setSupportMobile(Long supportMobile) {
        this.supportMobile = supportMobile;
    }

    public String getSuperNodeId() {
        return superNodeId;
    }

    public void setSuperNodeId(String superNodeId) {
        this.superNodeId = superNodeId == null ? null : superNodeId.trim();
    }
}