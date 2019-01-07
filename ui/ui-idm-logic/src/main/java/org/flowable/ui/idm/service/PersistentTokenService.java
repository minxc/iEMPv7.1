package org.flowable.ui.idm.service;

import org.flowable.idm.api.Token;
import org.flowable.idm.api.User;

/**
 * @author Joram Barrez
 * @author Tijs Rademakers
 */
public interface PersistentTokenService {

    Token getPersistentToken(String tokenId);

    Token getPersistentToken(String tokenId, boolean invalidateCacheEntry);

    Token saveAndFlush(Token persistentToken);

    void delete(Token persistentToken);

    public Token createToken(User user, String remoteAddress, String userAgent);

}
