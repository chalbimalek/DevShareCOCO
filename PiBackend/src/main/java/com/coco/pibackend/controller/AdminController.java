package com.coco.pibackend.controller;

import com.coco.pibackend.Entity.Role;
import com.coco.pibackend.Entity.User;
import com.coco.pibackend.repository.UserRepository;
import com.coco.pibackend.response.MessageResponse;
import com.coco.pibackend.service.AdminServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController

@Tag(name = "Admin")

public class AdminController {
    @Autowired
    AdminServices adminServices;
    @Autowired
    UserRepository userRepository;

    @Operation(description = "getAllUsers")
    @GetMapping(path = "/getAllUsers")
    public List<User> getAllUsers() {
        return adminServices.getall();
    }

    @Operation(description = "Update user role")
    @PostMapping(path = "/updateUser/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody Integer roleId) {
        adminServices.UpdateROle(id, roleId);
    }


    @Operation(description = "getAllRole")
    @GetMapping(path = "/getAllRole")
    List<Role> getAllRole() {
        return adminServices.getAllROles();
    }
    @Operation(description = "delete user by id")
    @DeleteMapping("/deleteUser/{id}")
    void deleteUser(@PathVariable Long id) {
        adminServices.deleteUserById(id);
    }


    @GetMapping("/FindUserId/{id}")
    public Optional<User> retrieveUser(@PathVariable("id") long id) {
        return adminServices.retrieveUser(id);
    }
    @PutMapping("/UpdateUser/{id}")
    public User updateUserById(@PathVariable("id") long id, @RequestBody User updatedUser) {
        return adminServices.updateUserById(id, updatedUser);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
    @PostMapping("/dashboard/clubs/uploadImage/{id}")
    public User handleImageFileUpload(@RequestParam("fileImage") MultipartFile fileImage, @PathVariable long id) {
        return adminServices.handleImageFileUpload(fileImage,id);
    }
    @PostMapping("/follow/{userId}/{userToFollowId}")
    public ResponseEntity<?> followUser(@PathVariable Long userId, @PathVariable Long userToFollowId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<User> optionalUserToFollow = userRepository.findById(userToFollowId);

        if (optionalUser.isPresent() && optionalUserToFollow.isPresent()) {
            User user = optionalUser.get();
            User userToFollow = optionalUserToFollow.get();

            user.follow(userToFollow);
            userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("Successfully followed user."));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("User or user to follow not found."));
        }
    }

    @PostMapping("/unfollow/{userId}/{userToUnfollowId}")
    public ResponseEntity<?> unfollowUser(@PathVariable Long userId, @PathVariable Long userToUnfollowId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<User> optionalUserToUnfollow = userRepository.findById(userToUnfollowId);

        if (optionalUser.isPresent() && optionalUserToUnfollow.isPresent()) {
            User user = optionalUser.get();
            User userToUnfollow = optionalUserToUnfollow.get();

            user.unfollow(userToUnfollow);
            userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("Successfully unfollowed user."));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("User or user to unfollow not found."));
        }
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<Set<User>> getFollowersByUserId(@PathVariable Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Set<User> followers = user.getFollowers();
            return ResponseEntity.ok(followers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = adminServices.getUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getttuser/id")
    public long getUserIdFromUsername(@RequestParam String username){
        return adminServices.getUserIdFromUsername(username);
    }

}
