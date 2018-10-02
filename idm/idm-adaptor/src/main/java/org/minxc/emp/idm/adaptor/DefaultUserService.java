package org.minxc.emp.idm.adaptor;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.minxc.emp.core.util.BeanCopierUtils;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.idm.api.constant.GroupTypeConstant;
import org.minxc.emp.idm.api.model.User;
import org.minxc.emp.idm.api.model.UserRole;
import org.minxc.emp.idm.api.model.dto.UserDto;
import org.minxc.emp.idm.api.model.dto.UserRoleDto;
import org.minxc.emp.idm.api.service.UserService;
import org.minxc.emp.idm.impl.manager.GroupManager;
import org.minxc.emp.idm.impl.manager.UserManager;
import org.minxc.emp.idm.impl.manager.UserRoleManager;
import org.minxc.emp.idm.impl.model.UserEntity;
import org.minxc.emp.idm.impl.model.UserRoleEntity;
import org.springframework.stereotype.Service;

/**
 * 
* 项目名称：idm-adaptor   
* 类名称：DefaultUserService   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年9月3日 上午12:15:53   
* 修改人：Xianchang.min   
* 修改时间：2018年9月3日 上午12:15:53   
* 修改备注：   
* @version  1.0  
*
 */


@SuppressWarnings("unchecked")
@Service(value = "userService")
public class DefaultUserService implements UserService {
    @Resource
    private  UserManager userManager;
    @Resource
    private  GroupManager groupManager;
    @Resource
    private  UserRoleManager userRoleManager;

    @Override
    public User getUserById(String userId) {
    	User user = userManager.get(userId);
        return BeanCopierUtils.transformBean(user, UserDto.class);
    }

    @Override
    public User getUserByAccount(String account) {
    	User user =  userManager.getByAccount(account);
    	return BeanCopierUtils.transformBean(user, UserDto.class);
    }

    @Override
    public List<User> getUserListByGroup(String groupType, String groupId) {
        //此处可以根据不同的groupType去调用真实的实现：如角色下的人，组织下的人
    	
    	List<UserEntity> userList  = null;
        if (groupType.equals(GroupTypeConstant.ORG.key())) {
        	userList = userManager.getUserListByOrgId(groupId);
        }

        if (groupType.equals(GroupTypeConstant.ROLE.key())) {
        	userList =   userManager.getUserListByRoleId(groupId);
        }
        if (groupType.equals(GroupTypeConstant.POSITION.key())) {
        	userList =   userManager.getListByRelId(groupId);
        }
       
        if(BeanUtils.isNotEmpty(userList)) {
        	return (List)BeanCopierUtils.transformList(userList, UserDto.class);
        }
        
        return Collections.emptyList();
    }

	@Override
	public List<UserRole> getUserRole(String userId) {
		List<UserRoleEntity> userRole = userRoleManager.getListByUserId(userId);
		
		return  (List)BeanCopierUtils.transformList(userRole, UserRoleDto.class);
	}


}
