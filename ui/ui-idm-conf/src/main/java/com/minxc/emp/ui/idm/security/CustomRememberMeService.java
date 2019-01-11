package com.minxc.emp.ui.idm.security;

import org.flowable.idm.api.Token;
import org.flowable.idm.api.User;

public interface CustomRememberMeService {

    Token createAndInsertPersistentToken(User user, String remoteAddress, String userAgent);
}
