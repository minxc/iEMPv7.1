package org.minxc.emp.idm.adaptor;


import org.minxc.emp.core.util.BeanCopierUtils;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.idm.api.constant.GroupTypeConstant;
import org.minxc.emp.idm.api.model.Group;
import org.minxc.emp.idm.api.model.GroupType;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.idm.api.model.dto.GroupDto;
import org.minxc.emp.idm.api.service.GroupService;
import org.minxc.emp.idm.impl.manager.GroupManager;
import org.minxc.emp.idm.impl.manager.GroupRelationManager;
import org.minxc.emp.idm.impl.manager.RoleManager;
import org.minxc.emp.idm.impl.manager.UserManager;
import org.minxc.emp.idm.impl.manager.UserRoleManager;
import org.minxc.emp.idm.impl.model.UserEntity;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户与组关系的实现类：通过用户找组，通过组找人等
 *
 * @author Administrator
 */
@Service("userGroupService")
public class DefaultGroupService implements GroupService {
    @Resource
    private UserManager userManager;

    @Resource
    private GroupManager groupManager;

    @Resource
    private GroupRelationManager groupRelManager;

    @Resource
    private UserRoleManager userRoleManager;

    @Resource
    private RoleManager roleManager;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Group> getGroupsByGroupTypeUserId(String groupType, String userId) {
    	List listGroup  = null;
    	
        if (groupType.equals(GroupTypeConstant.ORG.key())) {
        	listGroup = (List) groupManager.getByUserId(userId);
        }
        if (groupType.equals(GroupTypeConstant.ROLE.key())) {
        	listGroup = (List) roleManager.getListByUserId(userId);
        }
        if (groupType.equals(GroupTypeConstant.POSITION.key())) {
        	listGroup = (List) groupRelManager.getListByUserId(userId);
        }
        
        if(listGroup != null) {
        	return (List)BeanCopierUtils.transformList(listGroup, GroupDto.class);
        }
        
        return null;
    }

    @Override
    public Map<String, List<Group>> getAllGroupByAccount(String account) {
    	UserEntity user = userManager.getByAccount(account);
    	if(user == null) return Collections.EMPTY_MAP;
    	
    	return getAllGroupByUserId(user.getId());
    }

    
    @Override
    public Map<String, List<Group>> getAllGroupByUserId(String userId) {
        Map<String, List<Group>> listMap = Maps.newHashMap();
        
        List<Group> listOrg = (List) groupManager.getByUserId(userId);
        if (BeanUtils.isNotEmpty(listOrg)) {
        	List<Group> groupList = (List)BeanCopierUtils.transformList(listOrg, GroupDto.class);
            listMap.put(GroupTypeConstant.ORG.key(), groupList);
        }
        List<Group> listRole = (List) roleManager.getListByUserId(userId);
        if (BeanUtils.isNotEmpty(listRole)) {
        	List<Group> groupList = (List)BeanCopierUtils.transformList(listRole, GroupDto.class);
            listMap.put(GroupTypeConstant.ROLE.key(), groupList);
        }
        List<Group> listOrgRel = (List) groupRelManager.getListByUserId(userId);
        if (BeanUtils.isNotEmpty(listOrgRel)) {
        	List<Group> groupList = (List)BeanCopierUtils.transformList(listOrgRel, GroupDto.class);
            listMap.put(GroupTypeConstant.POSITION.key(), groupList);
        }
        return listMap;
    }


    /**
     * 根据用户ID获取所有组
     */
    @Override
    public List<Group> getGroupsByUserId(String userId) {
    	
        List<Group> listMap = Lists.newArrayList();
        List<Group> listOrg = (List) groupManager.getByUserId(userId);
        if (BeanUtils.isNotEmpty(listOrg)) {
            listMap.addAll(listOrg);
        }
        List<Group> listRole = (List) roleManager.getListByUserId(userId);
        if (BeanUtils.isNotEmpty(listRole)) {
            listMap.addAll(listRole);
        }
        List<Group> listOrgRel = (List) groupRelManager.getListByUserId(userId);
        if (BeanUtils.isNotEmpty(listOrgRel)) {
            listMap.addAll(listOrgRel);
        }
        
        //转换成GROUP DTO
        List<Group> groupList = (List)BeanCopierUtils.transformList(listMap, GroupDto.class);
        
        return groupList;
    }

    /**
     * 根据组类别和组ID获取组定义
     */
    @Override
    public Group getById(String groupType, String groupId) {
    	Group group = null;
        if (groupType.equals(GroupTypeConstant.ORG.key())) {
        	group = groupManager.get(groupId);
        }
        if (groupType.equals(GroupTypeConstant.ROLE.key())) {
        	group = roleManager.get(groupId);
        }
        if (groupType.equals(GroupTypeConstant.POSITION.key())) {
        	group = groupRelManager.get(groupId);
        }
        
        group = BeanCopierUtils.transformBean(group, GroupDto.class);
        return group;
    }

    /**
     * 根据组类别和组编码获取组定义
     */
    @Override
    public Group getByCode(String groupType, String code) {
    	Group group = null;
    	
        if (groupType.equals(GroupTypeConstant.ORG.key())) {
        	group = groupManager.getByCode(code);
        }
        if (groupType.equals(GroupTypeConstant.ROLE.key())) {
        	group = roleManager.getByAlias(code);
        }
        if (groupType.equals(GroupTypeConstant.POSITION.key())) {
        	group = groupRelManager.getByCode(code);
        }
        
        group = BeanCopierUtils.transformBean(group, GroupDto.class);
        return group;
    }

    /**
     * 获取所有组类型
     */
    @Override
    public List<GroupType> getGroupTypes() {
        List<GroupType> list = new ArrayList<GroupType>();
        for (GroupTypeConstant e : GroupTypeConstant.values()) {
            GroupType type = new GroupType(e.key(), e.label());
            list.add(type);
        }
        return list;
    }

    @Override
    public Group getMainGroup(String userId) {
        return groupManager.getMainGroup(userId);
    }

}
