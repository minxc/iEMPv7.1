package com.minxc.emp.ui.idm.service;

import java.util.List;

import org.flowable.idm.api.Group;
import org.flowable.idm.api.Privilege;
import org.flowable.idm.api.User;

/**
 * Service for retrieving and changing privilege information.
 * 
 * @author Joram Barrez
 */
public interface PrivilegeService {

    Privilege findPrivilege(String id);

    List<Privilege> findPrivileges();

    List<User> findUsersWithPrivilege(String privilegeId);

    void addUserPrivilege(String privilegeId, String userId);

    void deleteUserPrivilege(String privilegeId, String userId);

    List<Group> findGroupsWithPrivilege(String privilegeId);

    void addGroupPrivilege(String privilegeId, String groupId);

    void deleteGroupPrivilege(String privilegeId, String groupId);

}
