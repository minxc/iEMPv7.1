package org.minxc.emp.bpm.api.model.def;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;


/**
 * 
* 项目名称：bpm-api   
* 类名称：NodeInit   
* 类描述：节点初始化   
* 创建人：Xianchang.min   
* 创建时间：2018年8月29日 下午10:28:34   
* 修改人：Xianchang.min   
* 修改时间：2018年8月29日 下午10:28:34   
* 修改备注：   
* @version  1.0  
*
 */
public class NodeInit implements Serializable{

	private static final long serialVersionUID = 39841748991056717L;
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
