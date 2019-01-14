package com.minxc.emp.ui.idm.service;

import java.util.List;

import org.minxc.emp.idm.api.model.Group;
import org.minxc.emp.idm.api.model.Privilege;
import org.minxc.emp.idm.api.model.User;

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
