package com.coco.pibackend.ServiceIMp;

import com.coco.pibackend.Entity.Role;
import com.coco.pibackend.Entity.User;
import com.coco.pibackend.Repo.RoleRepo;
import com.coco.pibackend.Repo.UserRepo;
import com.coco.pibackend.Service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServicesImpl implements AdminServices {
    @Autowired
    UserRepo userRepository;
    @Autowired
    RoleRepo roleRepository;
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