package org.minxc.emp.bpm.api.model.def;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * 节点初始化。
 */
public class NodeInit implements Serializable{

    // 节点ID
	@NotBlank(message="节点不能为空")
    private String nodeId = "";
    //描述
	@NotBlank(message="节点初始化描述不能为空")
    private String desc = "";
    //展示前置脚本
    private String beforeShow;
    //保存前置脚本
    private String whenSave;


    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBeforeShow() {
        return beforeShow;
    }

    public void setBeforeShow(String beforeShow) {
        this.beforeShow = beforeShow;
    }

    public String getWhenSave() {
        return whenSave;
    }

    public void setWhenSave(String whenSave) {
        this.whenSave = whenSave;
    }


}
