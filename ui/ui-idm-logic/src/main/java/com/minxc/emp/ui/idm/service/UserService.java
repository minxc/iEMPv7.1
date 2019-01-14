package com.minxc.emp.ui.idm.service;

import java.util.List;

import org.minxc.emp.idm.api.model.User;
import com.minxc.emp.ui.idm.model.UserInformation;

/**
 * @author Joram Barrez
 */
public interface UserService {

    List<User> getUsers(String filter, String sort, Integer start);

    long getUserCount(String filter, String sort, Integer start, String groupId);

    void updateUserDetails(String userId, String firstName, String lastName, String email);

    void bulkUpdatePassword(List<String> userIds, String newPassword);

    void deleteUser(String userId);

    User createNewUser(String id, String firstName, String lastName, String email, String password);

    UserInformation getUserInformation(String userId);

}
