package org.minxc.emp.model;

public class SystemSerialNOEntity {
    private String id;

    private String name;

    private String alias;

    private String rule;

    private Integer genType;

    private Long noLength;

    private String curDate;

    private Long initValue;

    private Long curValue;

    private Integer step;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule == null ? null : rule.trim();
    }

    public Integer getGenType() {
        return genType;
    }

    public void setGenType(Integer genType) {
        this.genType = genType;
    }

    public Long getNoLength() {
        return noLength;
    }

    public void setNoLength(Long noLength) {
        this.noLength = noLength;
    }

    public String getCurDate() {
        return curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate == null ? null : curDate.trim();
    }

    public Long getInitValue() {
        return initValue;
    }

    public void setInitValue(Long initValue) {
        this.initValue = initValue;
    }

    public Long getCurValue() {
        return curValue;
    }

    public void setCurValue(Long curValue) {
        this.curValue = curValue;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}