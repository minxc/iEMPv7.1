package org.minxc.emp.model;

public class BizPermissionEntity {
    private String id;

    private String boKey;

    private String objType;

    private String objVal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBoKey() {
        return boKey;
    }

    public void setBoKey(String boKey) {
        this.boKey = boKey == null ? null : boKey.trim();
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType == null ? null : objType.trim();
    }

    public String getObjVal() {
        return objVal;
    }

    public void setObjVal(String objVal) {
        this.objVal = objVal == null ? null : objVal.trim();
    }
}