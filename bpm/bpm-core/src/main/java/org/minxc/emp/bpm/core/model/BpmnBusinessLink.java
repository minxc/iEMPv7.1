package org.minxc.emp.bpm.core.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.minxc.emp.core.api.model.IdModel;

public class BpmnBusinessLink implements IdModel {
	
	private static final long serialVersionUID = -1336956872376220360L;
	protected String id;
	protected String defId;
	protected String o;
	protected String p;
	protected String q;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setDefId(String defId) {
		this.defId = defId;
	}

	public String getDefId() {
		return this.defId;
	}

	public void setInstId(String instId) {
		this.o = instId;
	}

	public String getInstId() {
		return this.o;
	}

	public String toString() {
		return new ToStringBuilder((Object) this).append("id", (Object) this.id).append("defId", (Object) this.defId)
				.append("instId", (Object) this.o).append("bizKey", (Object) this.p)
				.append("bizIdentify", (Object) this.q).toString();
	}

	public String getBizId() {
		return this.p;
	}

	public void setBizId(String bizId) {
		this.p = bizId;
	}

	public String getBizCode() {
		return this.q;
	}

	public void setBizCode(String bizCode) {
		this.q = bizCode;
	}
}