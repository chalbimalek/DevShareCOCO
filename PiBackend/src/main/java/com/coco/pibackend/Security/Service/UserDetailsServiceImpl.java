package com.coco.pibackend.Security.Service;


import com.coco.pibackend.Entity.User;
import com.coco.pibackend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepo userRepository;

    @Override
    @Transactional
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepository.findByUsername(username)

                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
            System.out.println(user.getUsername() +"aaaaaaaaaaaaaaaaaa");
            return UserDetailsImpl.build(user);
        }}
