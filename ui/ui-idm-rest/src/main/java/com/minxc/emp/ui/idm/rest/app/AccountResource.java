package com.minxc.emp.ui.idm.rest.app;

import javax.servlet.http.HttpServletRequest;

import com.minxc.emp.ui.idm.model.UserInformation;
import com.minxc.emp.ui.idm.service.UserService;
import org.minxc.emp.idm.api.model.Group;
import com.minxc.emp.ui.common.model.GroupRepresentation;
import com.minxc.emp.ui.common.model.UserRepresentation;
import com.minxc.emp.ui.common.security.SecurityUtils;
import com.minxc.emp.ui.common.service.exception.NotFoundException;
import com.minxc.emp.ui.common.service.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * REST controller for managing the current user's account.
 * 
 * @author Joram Barrez
 */
@RestController
@RequestMapping("/app")
public class AccountResource {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    /**
     * GET /rest/authenticate -> check if the user is authenticated, and return its full name.
     */
    @RequestMapping(value = "/rest/authenticate", method = RequestMethod.GET, produces = { "application/json" })
    public ObjectNode isAuthenticated(HttpServletRequest request) {
        String user = request.getRemoteUser();

        if (user == null) {
            throw new UnauthorizedException("Request did not contain valid authorization");
        }

        ObjectNode result = objectMapper.createObjectNode();
        result.put("login", user);
        return result;
    }

    /**
     * GET /rest/account -> get the current user.
     */
    @RequestMapping(value = "/rest/account", method = RequestMethod.GET, produces = "application/json")
    public UserRepresentation getAccount() {

        String userId = SecurityUtils.getCurrentFlowableAppUser().getUserObject().getUserId();
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
}
