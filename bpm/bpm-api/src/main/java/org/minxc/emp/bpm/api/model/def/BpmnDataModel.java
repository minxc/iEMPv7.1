package org.minxc.emp.bpm.api.model.def;

import java.io.Serializable;

/**
 * 流程的busBo定义。
 */
public class BpmnDataModel implements Serializable{

    /**
     * BO的名称
     */
    private String name = "";

    /**
     * BO的code
     */
    private String code = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


}
