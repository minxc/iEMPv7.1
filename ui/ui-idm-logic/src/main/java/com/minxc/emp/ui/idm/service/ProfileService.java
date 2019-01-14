package com.minxc.emp.ui.idm.service;

import java.io.InputStream;

import org.apache.commons.lang3.tuple.Pair;
import org.minxc.emp.idm.api.model.User;

/**
 * @author Joram Barrez
 */
public interface ProfileService {

    User updateProfile(String firstName, String lastName, String email);

    void changePassword(String originalPassword, String newPassword);

    Pair<String, InputStream> getProfilePicture();

    void uploadProfilePicture(String contentType, byte[] bytes);

}
