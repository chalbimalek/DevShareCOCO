package com.coco.pibackend.controller;

import com.coco.pibackend.Entity.User;
import com.coco.pibackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService  userService;

    @PostMapping("/adduser")
    public User addUser( @RequestBody User user) {
        return userService.addUser( user);
    }
    @PutMapping("/userupdate")
    public User updateUser(@RequestBody User user) {
    return userService.updateUser(user);
    }

    @DeleteMapping("/deleteuser/{id}")
    public void Delete( int id) {
        userService.Delete(id);
    }


    @GetMapping
    public List<User> getList() {
        return  userService.getList();
    }


    }
