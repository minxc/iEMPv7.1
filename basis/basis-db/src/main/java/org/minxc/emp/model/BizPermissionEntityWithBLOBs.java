package org.minxc.emp.model;

public class BizPermissionEntityWithBLOBs extends BizPermissionEntity {
    private String busObjMapJson;

    private String rightsJson;

    public String getBusObjMapJson() {
        return busObjMapJson;
    }

    public void setBusObjMapJson(String busObjMapJson) {
        this.busObjMapJson = busObjMapJson == null ? null : busObjMapJson.trim();
    }

    public String getRightsJson() {
        return rightsJson;
    }

    public void setRightsJson(String rightsJson) {
        this.rightsJson = rightsJson == null ? null : rightsJson.trim();
    }
}