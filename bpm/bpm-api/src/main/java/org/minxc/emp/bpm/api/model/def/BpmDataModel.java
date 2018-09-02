package org.minxc.emp.bpm.api.model.def;

import java.io.Serializable;

/**
 * 流程的busBo定义。
 */
public class BpmDataModel implements Serializable{

	private static final long serialVersionUID = -5911787992273115737L;

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
