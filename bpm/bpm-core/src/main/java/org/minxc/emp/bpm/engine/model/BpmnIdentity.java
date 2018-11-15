package org.minxc.emp.bpm.engine.model;

import java.util.LinkedHashSet;

import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.idm.api.model.User;

public class BpmnIdentity implements SystemIdentity {

	private static final long serialVersionUID = 4416404339210896051L;
	private String id;
	private String name;
	private String type;

	public BpmnIdentity() {
	}

	public BpmnIdentity(String id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public BpmnIdentity(User user) {
		this.id = user.getUserId();
		this.name = user.getFullname();
		this.type = "user";
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public int hashCode() {
		return this.id.hashCode() + this.type.hashCode();
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof BpmnIdentity)) {
			return false;
		}
		BpmnIdentity identity = (BpmnIdentity) obj;
		if (identity.type.equals(this.type) && identity.id.equals(this.id)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		BpmnIdentity id1 = new BpmnIdentity();
		id1.setId("1");
		id1.setType("user");
		BpmnIdentity id2 = new BpmnIdentity();
		id2.setId("1");
		id2.setType("user");
		LinkedHashSet<BpmnIdentity> list = new LinkedHashSet<BpmnIdentity>();
		list.add(id1);
		list.add(id2);
		list.remove(id2);
		System.out.println(list.size());
	}
}