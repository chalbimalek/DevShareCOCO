package com.coco.pibackend.service.serviceImpl;

import com.coco.pibackend.Entity.Role;
import com.coco.pibackend.Entity.User;
import com.coco.pibackend.repository.RoleRepository;
import com.coco.pibackend.repository.UserRepository;
import com.coco.pibackend.service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServicesImpl implements AdminServices {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public List<User> getall() {
        return userRepository.findAll();
    }

    @Override
    public void UpdateROle(Long id, String role) {

    }

    @Override
    public List<Role> getAllROles() {
         return roleRepository.findAll();
    }
}
