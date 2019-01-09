package com.minxc.emp.ui.idm.service;

import org.flowable.idm.api.IdmIdentityService;
import org.flowable.idm.api.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenServiceImpl extends AbstractIdmService implements TokenService {

    @Autowired
    protected IdmIdentityService identityService;

    @Override
    public Token findTokenById(String tokenId) {
        return identityService.createTokenQuery().tokenId(tokenId).singleResult();
    }

}
