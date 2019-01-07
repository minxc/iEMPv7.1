package org.flowable.ui.idm.rest.api;

import java.util.ArrayList;
import java.util.List;

import org.flowable.idm.api.Group;
import org.flowable.ui.common.model.GroupRepresentation;
import org.flowable.ui.common.service.exception.NotFoundException;
import org.flowable.ui.idm.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiGroupsResource {

    @Autowired
    protected GroupService groupService;

    @RequestMapping(value = "/idm/groups/{groupId}", method = RequestMethod.GET, produces = {"application/json"})
    public GroupRepresentation getGroupInformation(@PathVariable String groupId) {
        Group group = groupService.getGroup(groupId);
        if (group != null) {
            return new GroupRepresentation(group);

        } else {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/idm/groups", method = RequestMethod.GET, produces = {"application/json"})
    public List<GroupRepresentation> findGroupsByFilter(@RequestParam("filter") String filter) {
        List<GroupRepresentation> result = new ArrayList<>();
        List<Group> groups = groupService.getGroups(filter);
        for (Group group : groups) {
            result.add(new GroupRepresentation(group));
        }
        return result;
    }

}
