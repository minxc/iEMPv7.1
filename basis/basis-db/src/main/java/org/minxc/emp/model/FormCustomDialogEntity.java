package org.minxc.emp.model;

public class FormCustomDialogEntity {
    private String id;

    private String key;

    private String name;

    private String description;

    private String style;

    private String datasourceKey;

    private String datasourceName;

    private String objType;

    private String objName;

    private Short page;

    private Long pageSize;

    private Long width;

    private Long height;

    private Short isSystem;

    private Short multiple;

    private String treeConfigJson;

    private String dataSourceFrom;

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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public String getDatasourceKey() {
        return datasourceKey;
    }

    public void setDatasourceKey(String datasourceKey) {
        this.datasourceKey = datasourceKey == null ? null : datasourceKey.trim();
    }

    public String getDatasourceName() {
        return datasourceName;
    }

    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName == null ? null : datasourceName.trim();
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType == null ? null : objType.trim();
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName == null ? null : objName.trim();
    }

    public Short getPage() {
        return page;
    }

    public void setPage(Short page) {
        this.page = page;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Short getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Short isSystem) {
        this.isSystem = isSystem;
    }

    public Short getMultiple() {
        return multiple;
    }

    public void setMultiple(Short multiple) {
        this.multiple = multiple;
    }

    public String getTreeConfigJson() {
        return treeConfigJson;
    }

    public void setTreeConfigJson(String treeConfigJson) {
        this.treeConfigJson = treeConfigJson == null ? null : treeConfigJson.trim();
    }

    public String getDataSourceFrom() {
        return dataSourceFrom;
    }

    public void setDataSourceFrom(String dataSourceFrom) {
        this.dataSourceFrom = dataSourceFrom == null ? null : dataSourceFrom.trim();
    }
}