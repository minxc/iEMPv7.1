package org.minxc.emp.bpm.core.model;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.minxc.emp.core.api.model.CommonModel;

public class BpmnTaskStack implements CommonModel {
	/** 
	
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	
	*/ 
	private static final long serialVersionUID = -7674693848073905484L;
	
	protected String id;
	protected String taskId;
	protected String o;
	protected String parentId;
	protected String R;
	protected String nodeName;
	protected Date startTime;
	protected Date endTime;
	protected Short aj;
	protected String path;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskId() {
		return this.taskId;
	}

	public void setInstId(String instId) {
		this.o = instId;
	}

	public String getInstId() {
		return this.o;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setNodeId(String nodeId) {
		this.R = nodeId;
	}

	public String getNodeId() {
		return this.R;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeName() {
		return this.nodeName;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setIsMulitiTask(Short isMulitiTask) {
		this.aj = isMulitiTask;
	}

	public Short getIsMulitiTask() {
		return this.aj;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return this.path;
	}

	public String toString() {
		return new ToStringBuilder((Object) this).append("id", (Object) this.id).append("taskId", (Object) this.taskId)
				.append("instId", (Object) this.o).append("parentId", (Object) this.parentId)
				.append("nodeId", (Object) this.R).append("nodeName", (Object) this.nodeName)
				.append("startTime", (Object) this.startTime).append("endTime", (Object) this.endTime)
				.append("isMulitiTask", (Object) this.aj).append("path", (Object) this.path).toString();
	}

	public String getCreateBy() {
		return null;
	}

	public void setCreateBy(String createBy) {
	}

	public Date getCreateTime() {
		return null;
	}

	public void setCreateTime(Date createtime) {
	}

	public Date getUpdateTime() {
		return null;
	}

	public void setUpdateTime(Date updatetime) {
	}

	public String getUpdateBy() {
		return null;
	}

	public void setUpdateBy(String updateBy) {
	}
}