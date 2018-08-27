package org.minxc.emp.model;

public class BizColumnEntity {
    private String id;

    private String tableId;

    private String key;

    private String name;

    private String type;

    private Long length;

    private Long isDecimal;

    private Short required;

    private Short isPrimary;

    private String defaultValue;

    private String comments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId == null ? null : tableId.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getIsDecimal() {
        return isDecimal;
    }

    public void setIsDecimal(Long isDecimal) {
        this.isDecimal = isDecimal;
    }

    public Short getRequired() {
        return required;
    }

    public void setRequired(Short required) {
        this.required = required;
    }

    public Short getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Short isPrimary) {
        this.isPrimary = isPrimary;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }
}