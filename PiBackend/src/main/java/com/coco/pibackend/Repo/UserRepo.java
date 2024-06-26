package com.coco.pibackend.Repo;


import com.coco.pibackend.Entity.Comment;
import com.coco.pibackend.Entity.Post;
import com.coco.pibackend.Entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);


    User findIdByUsername(String username);
}
