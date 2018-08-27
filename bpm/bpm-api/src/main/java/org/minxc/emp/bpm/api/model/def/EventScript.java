package org.minxc.emp.bpm.api.model.def;

import org.minxc.emp.bpm.api.constant.ScriptType;

/**
 * 事件脚本。
 */
public class EventScript {

    private ScriptType scriptType;

    private String content = "";

    public EventScript() {
    }

    public EventScript(ScriptType scriptType, String content) {
        this.scriptType = scriptType;
        this.content = content;
    }

    public ScriptType getScriptType() {
        return scriptType;
    }

    public void setScriptType(ScriptType scriptType) {
        this.scriptType = scriptType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
