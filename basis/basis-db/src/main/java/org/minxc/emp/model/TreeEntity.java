package org.minxc.emp.model;

public class TreeEntity {
    private String id;

    private String key;

    private String name;

    private String description;

    private Short isSystem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Short getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Short isSystem) {
        this.isSystem = isSystem;
    }
}