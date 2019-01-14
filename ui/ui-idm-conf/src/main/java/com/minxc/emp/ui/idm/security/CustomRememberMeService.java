package com.minxc.emp.ui.idm.security;

import org.minxc.emp.idm.api.model.Token;
import org.minxc.emp.idm.api.model.User;

public interface CustomRememberMeService {

    Token createAndInsertPersistentToken(User user, String remoteAddress, String userAgent);
}
