package com.coco.pibackend.service;


import com.coco.pibackend.Dto.UpdateProfileDto;
import com.coco.pibackend.Entity.User;
import com.coco.pibackend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private UserRepository userRepository;


    public void updateUserProfile(Long userId, UpdateProfileDto updateProfileDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(updateProfileDto.getFirstName());
            user.setLasteName(updateProfileDto.getLasteName());
            user.setCin(updateProfileDto.getCin());
            user.setPhone(updateProfileDto.getPhone());
            userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }
    }
}
