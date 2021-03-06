package com.minxc.emp.ui.idm.rest.app;


import java.util.ArrayList;
import java.util.List;

import com.minxc.emp.ui.idm.model.AddGroupPrivilegeRepresentation;
import com.minxc.emp.ui.idm.model.AddUserPrivilegeRepresentation;
import com.minxc.emp.ui.idm.model.PrivilegeRepresentation;
import com.minxc.emp.ui.idm.service.PrivilegeService;
import org.minxc.emp.idm.api.model.Group;
import org.minxc.emp.idm.api.model.Privilege;
import org.minxc.emp.idm.api.model.User;
import com.minxc.emp.ui.common.model.GroupRepresentation;
import com.minxc.emp.ui.common.model.UserRepresentation;
import com.minxc.emp.ui.common.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/app")
public class IdmPrivilegesResource {

    @Autowired
    protected PrivilegeService privilegeService;

    @RequestMapping(value = "/rest/admin/privileges", method = RequestMethod.GET)
    public List<PrivilegeRepresentation> getPrivileges() {
        List<Privilege> privileges = privilegeService.findPrivileges();
        List<PrivilegeRepresentation> representations = new ArrayList<>(privileges.size());
        for (Privilege privilege : privileges) {
            representations.add(new PrivilegeRepresentation(privilege.getId(), privilege.getName()));
        }
        return representations;
    }

    @RequestMapping(value = "/rest/admin/privileges/{privilegeId}", method = RequestMethod.GET)
    public PrivilegeRepresentation getPrivilege(@PathVariable String privilegeId) {

        Privilege privilege = privilegeService.findPrivilege(privilegeId);

        if (privilege != null) {
            PrivilegeRepresentation privilegeRepresentation = new PrivilegeRepresentation();
            privilegeRepresentation.setId(privilege.getId());
            privilegeRepresentation.setName(privilege.getName());

            List<User> users = privilegeService.findUsersWithPrivilege(privilegeId);
            for (User user : users) {
                privilegeRepresentation.addUser(new UserRepresentation(user));
            }

            List<Group> groups = privilegeService.findGroupsWithPrivilege(privilegeId);
            for (Group group : groups) {
                privilegeRepresentation.addGroup(new GroupRepresentation(group));
            }

            return privilegeRepresentation;
        } else {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/rest/admin/privileges/{privilegeId}/users", method = RequestMethod.GET)
    public List<UserRepresentation> getUsers(@PathVariable String privilegeId) {
        return getPrivilege(privilegeId).getUsers();
    }

    @RequestMapping(value = "/rest/admin/privileges/{privilegeId}/users", method = RequestMethod.POST)
    public void addUserPrivilege(@PathVariable String privilegeId,
                                 @RequestBody AddUserPrivilegeRepresentation representation) {
        privilegeService.addUserPrivilege(privilegeId, representation.getUserId());
    }

    @RequestMapping(value = "/rest/admin/privileges/{privilegeId}/users/{userId}", method = RequestMethod.DELETE)
    public void deleteUserPrivilege(@PathVariable String privilegeId, @PathVariable String userId) {
        privilegeService.deleteUserPrivilege(privilegeId, userId);
    }

    @RequestMapping(value = "/rest/admin/privileges/{privilegeId}/groups", method = RequestMethod.GET)
    public List<GroupRepresentation> getGroups(@PathVariable String privilegeId) {
        return getPrivilege(privilegeId).getGroups();
    }

    @RequestMapping(value = "/rest/admin/privileges/{privilegeId}/groups", method = RequestMethod.POST)
    public void addGroupPrivilege(@PathVariable String privilegeId,
                                  @RequestBody AddGroupPrivilegeRepresentation representation) {
        privilegeService.addGroupPrivilege(privilegeId, representation.getGroupId());
    }

    @RequestMapping(value = "/rest/admin/privileges/{privilegeId}/groups/{groupId}", method = RequestMethod.DELETE)
    public void deleteGroupPrivilege(@PathVariable String privilegeId, @PathVariable String groupId) {
        privilegeService.deleteGroupPrivilege(privilegeId, groupId);
    }

}
