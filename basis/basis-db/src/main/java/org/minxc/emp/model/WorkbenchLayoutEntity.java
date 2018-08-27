package org.minxc.emp.model;

import java.util.Date;

public class WorkbenchLayoutEntity {
    private String id;

    private String panelId;

    private Long custWidth;

    private Long custHeight;

    private Long seq;

    private String userId;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPanelId() {
        return panelId;
    }

    public void setPanelId(String panelId) {
        this.panelId = panelId == null ? null : panelId.trim();
    }

    public Long getCustWidth() {
        return custWidth;
    }

    public void setCustWidth(Long custWidth) {
        this.custWidth = custWidth;
    }

    public Long getCustHeight() {
        return custHeight;
    }

    public void setCustHeight(Long custHeight) {
        this.custHeight = custHeight;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}