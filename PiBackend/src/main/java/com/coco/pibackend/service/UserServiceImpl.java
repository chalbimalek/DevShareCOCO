package com.coco.pibackend.service;


import com.coco.pibackend.Entity.User;
import com.coco.pibackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

private final FileStorageService fileStorageService;
    private final UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void Delete(int id) {
        userRepository.deleteById((long) id);
    }

    @Override
    public List<User> getList() {
        return userRepository.findAll();
    }


}
