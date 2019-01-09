package com.minxc.emp.ui.common.service.idm;

import java.util.List;

import com.minxc.emp.ui.common.model.RemoteGroup;
import com.minxc.emp.ui.common.model.RemoteToken;
import com.minxc.emp.ui.common.model.RemoteUser;

public interface RemoteIdmService {

    RemoteUser authenticateUser(String username, String password);

    RemoteToken getToken(String tokenValue);

    RemoteUser getUser(String userId);

    List<RemoteUser> findUsersByNameFilter(String filter);
    
    List<RemoteUser> findUsersByGroup(String groupId);
    
    RemoteGroup getGroup(String groupId);

    List<RemoteGroup> findGroupsByNameFilter(String filter);

}
