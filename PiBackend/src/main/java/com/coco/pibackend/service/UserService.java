package com.coco.pibackend.service;

import com.coco.pibackend.Entity.User;

import java.util.List;

public interface UserService {

    User addUser(User user);
    User updateUser (User user);
    void Delete(int id);
    List<User> getList();
}
