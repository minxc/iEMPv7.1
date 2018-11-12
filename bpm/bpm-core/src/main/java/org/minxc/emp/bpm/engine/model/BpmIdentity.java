package org.minxc.emp.bpm.engine.model;

import java.io.PrintStream;
import java.util.LinkedHashSet;

import org.minxc.emp.basis.api.model.SystemIdentity;
import org.minxc.emp.idm.api.model.User;

public class BpmIdentity implements SystemIdentity {
	private static final long serialVersionUID = 4416404339210896051L;
	private String id;
	private String name;
	private String type;

	public BpmIdentity() {
	}

	public BpmIdentity(String id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public BpmIdentity(User user) {
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
		if (!(obj instanceof BpmIdentity)) {
			return false;
		}
		BpmIdentity identity = (BpmIdentity) obj;
		if (identity.type.equals(this.type) && identity.id.equals(this.id)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		BpmIdentity id1 = new BpmIdentity();
		id1.setId("1");
		id1.setType("user");
		BpmIdentity id2 = new BpmIdentity();
		id2.setId("1");
		id2.setType("user");
		LinkedHashSet<BpmIdentity> list = new LinkedHashSet<BpmIdentity>();
		list.add(id1);
		list.add(id2);
		list.remove(id2);
		System.out.println(list.size());
	}
}