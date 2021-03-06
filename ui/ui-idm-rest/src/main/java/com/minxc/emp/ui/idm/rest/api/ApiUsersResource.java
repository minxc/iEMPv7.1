package com.minxc.emp.ui.idm.rest.api;

import java.util.ArrayList;
import java.util.List;

import com.minxc.emp.ui.idm.model.UserInformation;
import com.minxc.emp.ui.idm.service.UserService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import com.minxc.emp.ui.common.model.GroupRepresentation;
import com.minxc.emp.ui.common.model.UserRepresentation;
import com.minxc.emp.ui.common.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiUsersResource {

    @Autowired
    protected UserService userService;

    @RequestMapping(value = "/idm/users/{userId}", method = RequestMethod.GET, produces = {"application/json"})
    public UserRepresentation getUserInformation(@PathVariable String userId) {
        UserInformation userInformation = userService.getUserInformation(userId);
        if (userInformation != null) {
            UserRepresentation userRepresentation = new UserRepresentation(userInformation.getUser());
            if (userInformation.getGroups() != null) {
                for (Group group : userInformation.getGroups()) {
                    userRepresentation.getGroups().add(new GroupRepresentation(group));
                }
            }
            if (userInformation.getPrivileges() != null) {
                for (String privilege : userInformation.getPrivileges()) {
                    userRepresentation.getPrivileges().add(privilege);
                }
            }
            return userRepresentation;
        } else {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/idm/users", method = RequestMethod.GET, produces = {"application/json"})
    public List<UserRepresentation> findUsersByFilter(@RequestParam("filter") String filter) {
        List<User> users = userService.getUsers(filter, null, null);
        List<UserRepresentation> result = new ArrayList<>();
        for (User user : users) {
            result.add(new UserRepresentation(user));
        }
        return result;
    }

}
