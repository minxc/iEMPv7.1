package com.minxc.emp.ui.idm.service;

import org.flowable.idm.api.Token;

public interface TokenService {

    Token findTokenById(String tokenId);

}