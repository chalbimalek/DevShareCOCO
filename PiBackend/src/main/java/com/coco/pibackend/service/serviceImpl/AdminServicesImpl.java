package com.coco.pibackend.service.serviceImpl;

import com.coco.pibackend.Entity.ERole;
import com.coco.pibackend.Entity.Role;
import com.coco.pibackend.service.FileStorageService;
import com.coco.pibackend.Entity.User;
import com.coco.pibackend.repository.RoleRepository;
import com.coco.pibackend.repository.UserRepository;
import com.coco.pibackend.service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminServicesImpl implements AdminServices {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    FileStorageService fileStorageService;
    @Override
    public List<User> getall() {
        return userRepository.findAll();
    }

    @Override
    public void UpdateROle(Long userId, Integer roleId) {
        // Fetch the user by their ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Fetch the role by its ID
        Role role = roleRepository.findById(Long.valueOf(roleId))
                .orElseThrow(() -> new RuntimeException("Role not found for id: " + roleId));

        // To replace existing roles with the new role
        Set<Role> newRoles = new HashSet<>();
        newRoles.add(role);
        user.setRoles(newRoles);

        // Alternatively, to add the new role to existing roles (uncomment if needed)
        // Set<Role> existingRoles = new HashSet<>(user.getRoles());
        // existingRoles.add(role);
        // user.setRoles(existingRoles);

        // Save the updated user
        userRepository.save(user);
    }


    @Override
    public List<Role> getAllROles() {
         return roleRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }



    @Override
    public User updateUserById(long id, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setRoles(updatedUser.getRoles());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setLasteName(updatedUser.getLasteName());
            // Update other fields as needed
            return userRepository.save(existingUser);
        } else {
            // Handle user not found
            return null;
        }
    }

    @Override
    public Optional<User> retrieveUser(long id){return userRepository.findById(id);}

    @Override
    public User handleImageFileUpload(MultipartFile fileImage, long id) {
        if (fileImage.isEmpty()) {
            return null;
        }
        String fileName = fileStorageService.storeFile(fileImage);
        User user = userRepository.findById(id).orElse(null);
        user.setImgUrl(fileName);
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }
    @Override
    public long getUserIdFromUsername(String username) {
        User user = userRepository.findIdByUsername(username);
        if (user != null) {
            return user.getId();
        } else {
            // Handle case when user is not found
            return -1; // Or throw an exception, depending on your requirements
        }

    }

}
