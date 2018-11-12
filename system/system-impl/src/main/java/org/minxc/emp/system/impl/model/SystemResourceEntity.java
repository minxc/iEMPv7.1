package org.minxc.emp.system.impl.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.minxc.emp.core.api.model.IdModel;
import org.minxc.emp.core.api.model.TreeModel;
import org.minxc.emp.system.api.model.SystemResource;

/**
 * 
 * 项目名称：system-impl 类名称：SysResource 类子系统资源 实体对象 创建人：Xianchang.min
 * 创建时间：2018年9月3日 下午10:08:18 修改人：Xianchang.min 修改时间：2018年9月3日 下午10:08:18 修改备注：
 * 
 * @version 1.0
 *
 */
public class SystemResourceEntity implements TreeModel<SystemResourceEntity>, IdModel, SystemResource {

	private static final long serialVersionUID = 1858224430587897285L;

	/**
	 * 主键
	 */
	protected String id;

	/**
	 * 子系统ID
	 */
	protected String applicationId;

	/**
	 * 资源别名
	 */
	protected String alias;

	/**
	 * 资源名
	 */
	protected String name;

	/**
	 * 默认地址
	 */
	protected String defaultUrl;

	/**
	 * 显示到菜单(1,显示,0 ,不显示)
	 */
	protected Integer enableMenu;

	/**
	 * 是否有子节点
	 */
	protected Integer hasChildren;

	/**
	 * OPENED_
	 */
	protected Integer opened;

	/**
	 * 图标
	 */
	protected String icon = "";

	/**
	 * 打开新窗口
	 */
	protected Integer newWindow;

	/**
	 * 排序
	 */
	protected Long seq;

	/**
	 * 父资源ID
	 */
	protected String parentId;

	/**
	 * 创建时间。
	 */
	protected Date createTime;

	/**
	 * 资源的URL列表
	 */
	protected List<ResourceLinkEntity> relResources = new ArrayList<ResourceLinkEntity>();

	protected List<SystemResourceEntity> children = new ArrayList<SystemResourceEntity>();

	/**
	 * 是否已分配给角色
	 */
	protected boolean checked = false;

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 返回 主键
	 *
	 * @return
	 */
	public String getId() {
		return this.id;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * 返回 资源别名
	 *
	 * @return
	 */
	public String getAlias() {
		return this.alias;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 资源名
	 *
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

	/**
	 * 返回 默认地址
	 *
	 * @return
	 */
	public String getDefaultUrl() {
		return this.defaultUrl;
	}

	public void setEnableMenu(Integer enableMenu) {
		this.enableMenu = enableMenu;
	}

	/**
	 * 返回 显示到菜单(1,显示,0 ,不显示)
	 *
	 * @return
	 */
	public Integer getEnableMenu() {
		return this.enableMenu;
	}

	public void setHasChildren(Integer hasChildren) {
		this.hasChildren = hasChildren;
	}

	/**
	 * 返回 是否有子节点
	 *
	 * @return
	 */
	public Integer getHasChildren() {
		return this.hasChildren;
	}

	public void setOpened(Integer opened) {
		this.opened = opened;
	}

	/**
	 * 返回 OPENED_
	 *
	 * @return
	 */
	public Integer getOpened() {
		return this.opened;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 返回 图标
	 *
	 * @return
	 */
	public String getIcon() {
		return this.icon;
	}

	public void setNewWindow(Integer newWindow) {
		this.newWindow = newWindow;
	}

	/**
	 * 返回 打开新窗口
	 *
	 * @return
	 */
	public Integer getNewWindow() {
		return this.newWindow;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	/**
	 * 返回 排序
	 *
	 * @return
	 */

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

//    public List<RelResource> getRelResources() {
//        return relResources;
//    }

	public void setRelResources(List<ResourceLinkEntity> relResources) {
		this.relResources = relResources;
	}

	/**
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemResourceEntity other = (SystemResourceEntity) obj;
		if (id.equals(other.id))
			return true;
		return false;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("applicationId", this.applicationId)
				.append("alias", this.alias).append("name", this.name).append("defaultUrl", this.defaultUrl)
				.append("enableMenu", this.enableMenu).append("hasChildren", this.hasChildren)
				.append("opened", this.opened).append("parentId", this.parentId).append("icon", this.icon)
				.append("newWindow", this.newWindow).append("seq", this.seq).toString();
	}

	@Override
	public List<SystemResourceEntity> getChildren() {
		return children;
	}

	public void setChildren(List<SystemResourceEntity> children) {
		this.children = children;
	}

	@Override
	public String getApplicationId() {
		return this.applicationId;
	}

	@Override
	public Long getSeq() {
		return this.seq;
	}

	@Override
	public List<ResourceLinkEntity> getRelatedResources() {
		return null;
	}
}