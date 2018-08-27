package org.minxc.emp.idm.impl.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 组织结构树
 */
public class OrgTreeEntity extends GroupEntity {

	private static final long serialVersionUID = -8724717293737152730L;
	public static final String ICON_COMORG = "/styles/theme/default/images/icons/u_darkblue/u_zzgl_darkblue.png";
	protected Long sn; /* 序号 */
	protected String icon; /* 图标 */
	protected boolean nocheck = false;
	/***/
	protected boolean chkDisabled = false;
	protected boolean click = true;
	protected String title = ""; // *title 默认为name 、如果name添加了 css 、则默认为 “” */

	public OrgTreeEntity() {
	}

	public OrgTreeEntity(String name, String id, String parentId, String icon) {
		setName(name);
		this.parentId = parentId;
		this.id = id;
		this.icon = icon;

	}

	/**
	 * GroupList2TreeList
	 */
	public static List<OrgTreeEntity> GroupList2TreeList(List<GroupEntity> groupList, String icon) {
		if (groupList == null || groupList.size() == 0)
			return Collections.emptyList();

		List<OrgTreeEntity> groupTreeList = new ArrayList<OrgTreeEntity>();
		for (GroupEntity group : groupList) {
			OrgTreeEntity grouptree = new OrgTreeEntity(group);
			grouptree.setIcon(icon);
			groupTreeList.add(grouptree);
		}
		return groupTreeList;
	}

	public OrgTreeEntity(GroupEntity group) {
		this.id = group.id;
		this.name = group.name;
		this.code = group.code;
		this.sn = group.sn;
		this.parentId = group.parentId;
		if (!this.name.contains("style=")) {
			this.title = name;
		}
	}

	@Override
	public void setName(String name) {
		this.name = name;
		// 将title 设置成name
		if ("".equals(title) && !this.name.contains("style=")) {
			this.title = name;
		}
	}

	;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isNocheck() {
		return nocheck;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}

	public boolean isChkDisabled() {
		return chkDisabled;
	}

	public boolean isClick() {
		return click;
	}

	public void setClick(boolean click) {
		this.click = click;
	}

	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

}
