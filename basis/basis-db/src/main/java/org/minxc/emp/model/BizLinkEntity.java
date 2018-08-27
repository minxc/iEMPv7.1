package org.minxc.emp.model;

public class BizLinkEntity extends BizLinkEntityKey {
    private String defId;

    private String instId;

    private String bizId;

    public String getDefId() {
        return defId;
    }

    public void setDefId(String defId) {
        this.defId = defId == null ? null : defId.trim();
    }

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId == null ? null : instId.trim();
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId == null ? null : bizId.trim();
    }
}