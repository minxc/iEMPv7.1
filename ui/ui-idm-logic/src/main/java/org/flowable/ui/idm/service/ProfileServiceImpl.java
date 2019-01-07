package org.flowable.ui.idm.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.flowable.idm.api.Picture;
import org.flowable.idm.api.User;
import org.flowable.ui.common.security.SecurityUtils;
import org.flowable.ui.common.service.exception.BadRequestException;
import org.flowable.ui.common.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author Joram Barrez
 */
@Service
@Transactional
public class ProfileServiceImpl extends AbstractIdmService implements ProfileService {

    public User updateProfile(String firstName, String lastName, String email) {
        User currentUser = SecurityUtils.getCurrentUserObject();

        // If user is not externally managed, we need the email address for login, so an empty email is not allowed
        if (StringUtils.isEmpty(email)) {
            throw new BadRequestException("Empty email is not allowed");
        }

        User user = identityService.createUserQuery().userId(currentUser.getId()).singleResult();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        identityService.saveUser(user);
        return user;
    }

    public void changePassword(String originalPassword, String newPassword) {
        User user = identityService.createUserQuery().userId(SecurityUtils.getCurrentUserId()).singleResult();
        if (!user.getPassword().equals(originalPassword)) {
            throw new NotFoundException();
        }
        user.setPassword(newPassword);
        identityService.updateUserPassword(user);
    }

    public Pair<String, InputStream> getProfilePicture() {
        Picture picture = identityService.getUserPicture(SecurityUtils.getCurrentUserId());
        if (picture != null) {
            InputStream in = new BufferedInputStream(new ByteArrayInputStream(picture.getBytes()));
            return Pair.of(picture.getMimeType(), in);
        }
        return null;
    }

    public void uploadProfilePicture(String contentType, byte[] bytes) {
        Picture picture = new Picture(bytes, contentType);
        identityService.setUserPicture(SecurityUtils.getCurrentUserId(), picture);
    }

}
