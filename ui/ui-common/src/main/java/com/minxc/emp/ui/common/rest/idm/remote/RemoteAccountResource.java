package com.minxc.emp.ui.common.rest.idm.remote;

import java.util.ArrayList;
import java.util.List;

import com.minxc.emp.ui.common.model.GroupRepresentation;
import com.minxc.emp.ui.common.model.RemoteGroup;
import com.minxc.emp.ui.common.model.RemoteUser;
import com.minxc.emp.ui.common.model.UserRepresentation;
import com.minxc.emp.ui.common.security.SecurityUtils;
import com.minxc.emp.ui.common.service.idm.RemoteIdmService;
import com.minxc.emp.ui.common.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class RemoteAccountResource {

    @Autowired
    private RemoteIdmService remoteIdmService;

    /**
     * GET /rest/account -> get the current user.
     */
    @RequestMapping(value = "/rest/account", method = RequestMethod.GET, produces = "application/json")
    public UserRepresentation getAccount() {
        UserRepresentation userRepresentation = null;
        String currentUserId = SecurityUtils.getCurrentUserId();
        if (currentUserId != null) {
            RemoteUser remoteUser = remoteIdmService.getUser(currentUserId);
            if (remoteUser != null) {
                userRepresentation = new UserRepresentation(remoteUser);

                if (remoteUser.getGroups() != null && remoteUser.getGroups().size() > 0) {
                    List<GroupRepresentation> groups = new ArrayList<>();
                    for (RemoteGroup remoteGroup : remoteUser.getGroups()) {
                        groups.add(new GroupRepresentation(remoteGroup));
                    }
                    userRepresentation.setGroups(groups);
                }

                if (remoteUser.getPrivileges() != null && remoteUser.getPrivileges().size() > 0) {
                    userRepresentation.setPrivileges(remoteUser.getPrivileges());
                }

            }
        }

        if (userRepresentation != null) {
            return userRepresentation;
        } else {
            throw new NotFoundException();
        }
    }

}
