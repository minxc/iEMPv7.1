package com.minxc.emp.ui.idm.cache;

import java.util.Collection;

import org.flowable.idm.api.User;
import org.springframework.security.core.GrantedAuthority;

/**
 * A cache of {@link User} objects.
 */
public interface UserCache {

    CachedUser getUser(String userId);

    CachedUser getUser(String userId, boolean throwExceptionOnNotFound, boolean throwExceptionOnInactive, boolean checkValidity);

    void putUser(String userId, CachedUser cachedUser);

    void invalidate(String userId);

    public static class CachedUser {

        private Collection<GrantedAuthority> grantedAuthorities;

        private User user;

        private long lastDatabaseCheck;

        public CachedUser(User user, Collection<GrantedAuthority> grantedAuthorities) {
            this.user = user;
            this.grantedAuthorities = grantedAuthorities;
            this.lastDatabaseCheck = System.currentTimeMillis();
        }

        public User getUser() {
            return user;
        }

        public Collection<GrantedAuthority> getGrantedAuthorities() {
            return grantedAuthorities;
        }

        public long getLastDatabaseCheck() {
            return lastDatabaseCheck;
        }

        public void setLastDatabaseCheck(long lastDatabaseCheck) {
            this.lastDatabaseCheck = lastDatabaseCheck;
        }

    }

}
