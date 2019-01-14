package com.minxc.emp.ui.idm.service;

import  org.minxc.emp.idm.api.model.Token;

public interface TokenService {

    Token findTokenById(String tokenId);

}
