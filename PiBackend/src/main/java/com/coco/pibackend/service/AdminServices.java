package com.coco.pibackend.service;

import com.coco.pibackend.Entity.Role;
import com.coco.pibackend.Entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface AdminServices {
    List<User> getall();
    void UpdateROle(Long userId, Integer roleId);
    List<Role> getAllROles();
    void deleteUserById(Long id);
     User updateUserById(long id, User updatedUser);
     Optional<User> retrieveUser(long id);
    User handleImageFileUpload(MultipartFile fileImage, long id);

    User getUserByUsername(String username);
    long getUserIdFromUsername(String username);
}
