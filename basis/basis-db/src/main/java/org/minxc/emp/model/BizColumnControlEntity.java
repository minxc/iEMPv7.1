package org.minxc.emp.model;

public class BizColumnControlEntity {
    private String id;

    private String columnId;

    private String type;

    private String config;

    private String validRule;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId == null ? null : columnId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config == null ? null : config.trim();
    }

    public String getValidRule() {
        return validRule;
    }

    public void setValidRule(String validRule) {
        this.validRule = validRule == null ? null : validRule.trim();
    }
}