package org.minxc.emp.model;

public class FormCustomDialogEntityWithBLOBs extends FormCustomDialogEntity {
    private String displayFieldsJson;

    private String conditionFieldsJson;

    private String returnFieldsJson;

    private String sortFieldsJson;

    public String getDisplayFieldsJson() {
        return displayFieldsJson;
    }

    public void setDisplayFieldsJson(String displayFieldsJson) {
        this.displayFieldsJson = displayFieldsJson == null ? null : displayFieldsJson.trim();
    }

    public String getConditionFieldsJson() {
        return conditionFieldsJson;
    }

    public void setConditionFieldsJson(String conditionFieldsJson) {
        this.conditionFieldsJson = conditionFieldsJson == null ? null : conditionFieldsJson.trim();
    }

    public String getReturnFieldsJson() {
        return returnFieldsJson;
    }

    public void setReturnFieldsJson(String returnFieldsJson) {
        this.returnFieldsJson = returnFieldsJson == null ? null : returnFieldsJson.trim();
    }

    public String getSortFieldsJson() {
        return sortFieldsJson;
    }

    public void setSortFieldsJson(String sortFieldsJson) {
        this.sortFieldsJson = sortFieldsJson == null ? null : sortFieldsJson.trim();
    }
}