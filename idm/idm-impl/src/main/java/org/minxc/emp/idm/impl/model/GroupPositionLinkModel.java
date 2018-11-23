/**
 * 
 */
package org.minxc.emp.idm.impl.model;

import org.minxc.emp.core.impl.model.AbstractCommonModel;
import org.minxc.emp.idm.api.constant.GroupTypeConstant;
import org.minxc.emp.idm.api.model.Group;
import org.minxc.emp.idm.api.model.GroupStructEnum;
import org.minxc.emp.idm.api.model.IdentityType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Xianchang.min TODO:逻辑处理有些问题？需要判断
 *
 */
@ToString()
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class GroupPositionLinkModel extends AbstractCommonModel implements Group {
	
	private static final long serialVersionUID = 2561071281429397782L;
	
	protected String id;
	protected String groupId;
	protected String positionId;
	protected String positionName;
	protected String positionCode;
	protected String groupName;
	protected String jobName;
	
	@Override
	public String getIdentityType() {
		return IdentityType.GROUP;
	}

	@Override
	public String getGroupId() {
		return this.groupId;
	}

	@Override
	public String getName() {
		return this.groupName;
	}

	@Override
	public String getGroupCode() {
		return this.positionCode;
	}

	@Override
	public Long getSeq() {
		return Long.valueOf(0);
	}

	
	@Override
	public String getGroupType() {
		return GroupTypeConstant.POSITION.key();
	}

	
	@Override
	public GroupStructEnum getStruct() {
		return GroupStructEnum.PLAIN;
	}

	
	@Override
	public String getParentId() {
		return null;
	}

	@Override
	public String getPath() {
		return null;
	}

}
