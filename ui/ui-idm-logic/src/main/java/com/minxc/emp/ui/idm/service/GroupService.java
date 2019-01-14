package com.minxc.emp.ui.idm.service;

import java.util.List;

import org.minxc.emp.idm.api.model.Group;
import org.minxc.emp.idm.api.model.User;

/**
 * @author Joram Barre
 */
public interface GroupService {

    List<Group> getGroups(String filter);

    List<Group> getGroupsForUser(String userId);

    Group getGroup(String groupId);

    List<User> getGroupUsers(String groupId, String filter, Integer page, Integer pageSize);

    long countTotalGroupUsers(String groupId, String filter, Integer page, Integer pageSize);

    Group createNewGroup(String id, String name, String type);

    Group updateGroupName(String groupId, String name);

    void deleteGroup(String groupId);

    void addGroupMember(String groupId, String userId);

    void deleteGroupMember(String groupId, String userId);

}
