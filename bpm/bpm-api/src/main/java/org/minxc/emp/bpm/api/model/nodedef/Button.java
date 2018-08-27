package org.minxc.emp.bpm.api.model.nodedef;

import java.io.Serializable;

/**
 * 流程节点按钮定义。
 */
public class Button implements Serializable {
    private static final long serialVersionUID = 1L;

    public Button() {
    }


    public Button(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }


    public Button(String name, String alias, String groovyScript, String configPage) {
        this.name = name;
        this.alias = alias;
        this.groovyScript = groovyScript;
        this.configPage = configPage;
    }

    /**
     * 按钮名
     */
    protected String name = "";
    /**
     * 按钮别名
     */
    protected String alias = "";

    /**
     * 前置脚本
     */
    protected String beforeScript = "";
    /**
     * 后置脚本
     */
    protected String afterScript = "";

    /**
     * 后台java脚本。
     */
    protected String groovyScript = "";
    /**
     * 按钮更多配置页
     */
    protected String configPage = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getBeforeScript() {
        return beforeScript;
    }

    public void setBeforeScript(String beforeScript) {
        this.beforeScript = beforeScript;
    }

    public String getAfterScript() {
        return afterScript;
    }

    public void setAfterScript(String afterScript) {
        this.afterScript = afterScript;
    }

    public String getGroovyScript() {
        return groovyScript;
    }

    public void setGroovyScript(String groovyScript) {
        this.groovyScript = groovyScript;
    }


    public String getConfigPage() {
        return configPage;
    }


    public void setConfigPage(String configPage) {
        this.configPage = configPage;
    }


    @Override
    public String toString() {
        return "[name=" + name + ", alias=" + alias + "]";
    }
}
