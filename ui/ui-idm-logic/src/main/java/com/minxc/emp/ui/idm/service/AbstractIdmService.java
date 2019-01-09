package com.minxc.emp.ui.idm.service;

import org.flowable.idm.api.IdmIdentityService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Joram Barrez
 */
public class AbstractIdmService {

    @Autowired
    protected IdmIdentityService identityService;

}
