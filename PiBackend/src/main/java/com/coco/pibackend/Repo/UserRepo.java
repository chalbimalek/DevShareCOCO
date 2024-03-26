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
public interface UserRepo extends JpaRepository<User,String> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    User findUserById(Long userId);
    List<User> findUsersByFollowerUsers(User user, Pageable pageable);
    List<User> findUsersByFollowingUsers(User user, Pageable pageable);
    List<User> findUsersByLikedPosts(Post post, Pageable pageable);
    List<User> findUsersByLikedComments(Comment comment, Pageable pageable);

    @Query(value = "select * from users u " +
            "where concat(u.first_name, ' ', u.last_name) like %:name% " +
            "order by u.first_name asc, u.last_name asc",
            nativeQuery = true)
    List<User> findUsersByName(String name, Pageable pageable);
}
