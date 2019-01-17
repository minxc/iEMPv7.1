package com.minxc.emp.ui.idm.security;

import java.util.ArrayList;
import java.util.Collection;

import com.minxc.emp.ui.idm.cache.UserCache;
import com.minxc.emp.ui.idm.model.UserInformation;
import com.minxc.emp.ui.idm.service.UserService;
import com.minxc.emp.ui.common.security.EMPAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class is called AFTER successful authentication, to populate the user object with additional details The default (no ldap) way of authentication is a bit hidden in Spring Security magic. But
 * basically, the user object is fetched from the db and the hashed password is compared with the hash of the provided password (using the Spring {@link StandardPasswordEncoder}).
 */
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService, CustomUserDetailService {

    @Autowired
    protected UserCache userCache;

    @Autowired
    protected IdmIdentityService identityService;

    @Autowired
    protected UserService userService;



    protected long userValidityPeriod;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {

        // This method is only called during the login.
        // All subsequent calls use the method with the long userId as parameter.
        // (Hence why the cache is NOT used here, but it is used in the loadByUserId)

        String actualLogin = login;
        User userFromDatabase = null;


            userFromDatabase = identityService.createUserQuery().userId(actualLogin).singleResult();


        // Verify user
        if (userFromDatabase == null) {
            throw new UsernameNotFoundException("User " + actualLogin + " was not found in the database");
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        UserInformation userInformation = userService.getUserInformation(userFromDatabase.getId());
        for (String privilege : userInformation.getPrivileges()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(privilege));
        }

        userCache.putUser(userFromDatabase.getId(), new UserCache.CachedUser(userFromDatabase, grantedAuthorities));
        return new EMPAppUser(userFromDatabase, actualLogin, grantedAuthorities);
    }

    @Transactional
    public UserDetails loadByUserId(final String userId) {
        UserCache.CachedUser cachedUser = userCache.getUser(userId, true, true, false); // Do not check for validity. This would lead to A LOT of db requests! For login, there is a validity period (see below)
        if (cachedUser == null) {
            throw new UsernameNotFoundException("User " + userId + " was not found in the database");
        }

        long lastDatabaseCheck = cachedUser.getLastDatabaseCheck();
        long currentTime = System.currentTimeMillis(); // No need to create a Date object. The Date constructor simply calls this method too!

        if (userValidityPeriod <= 0L || (currentTime - lastDatabaseCheck >= userValidityPeriod)) {

            userCache.invalidate(userId);
            cachedUser = userCache.getUser(userId, true, true, false); // Fetching it again will refresh data

            cachedUser.setLastDatabaseCheck(currentTime);
        }

        // The Spring security docs clearly state a new instance must be returned on every invocation
        User user = cachedUser.getUser();
        String actualUserId = user.getId();

        return new EMPAppUser(cachedUser.getUser(), actualUserId, cachedUser.getGrantedAuthorities());
    }

    public void setUserValidityPeriod(long userValidityPeriod) {
        this.userValidityPeriod = userValidityPeriod;
    }
}
