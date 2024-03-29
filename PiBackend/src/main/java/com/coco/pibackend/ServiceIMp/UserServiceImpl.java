package com.coco.pibackend.ServiceIMp;


import com.coco.pibackend.Entity.Comment;
import com.coco.pibackend.Entity.Post;
import com.coco.pibackend.Entity.User;
import com.coco.pibackend.Repo.UserRepo;
import com.coco.pibackend.Response.UserResponse;
import com.coco.pibackend.Security.JWT.AuthTokenFilter;
import com.coco.pibackend.Service.UserService;
import com.coco.pibackend.execption.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepo userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void Delete(long id) {
        userRepository.deleteById(String.valueOf(id));
    }

    @Override
    public List<User> getList() {
        return userRepository.findAll();
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
    public void followUser(String userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();        User authUser = userRepository.findByUsername(username).get();

        if (!authUser.getId().equals(userId)) {
            User userToFollow = getUserById(userId);
            authUser.getFollowingUsers().add(userToFollow);
            authUser.setFollowingCount(authUser.getFollowingCount() + 1);
            userToFollow.getFollowerUsers().add(authUser);
            userToFollow.setFollowerCount(userToFollow.getFollowerCount() + 1);
            userRepository.save(userToFollow);
            userRepository.save(authUser);
        } else {
            throw new InvalidOperationException();
        }
    }

    public void unfollowUser(String userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();        User authUser = userRepository.findByUsername(username).get();

        if (!authUser.getId().equals(userId)) {
            User userToUnfollow = getUserById(userId);
            authUser.getFollowingUsers().remove(userToUnfollow);
            authUser.setFollowingCount(authUser.getFollowingCount() - 1);
            userToUnfollow.getFollowerUsers().remove(authUser);
            userToUnfollow.setFollowerCount(userToUnfollow.getFollowerCount() - 1);
            userRepository.save(userToUnfollow);
            userRepository.save(authUser);
        } else {
            throw new InvalidOperationException();
        }
    }

   /* public List<UserResponse> getUserSearchResult(String key, Integer page, Integer size) {
        if (key.length() < 3) throw new InvalidOperationException();

        return userRepository.findUsersByName(
                key,
                PageRequest.of(page, size)
        ).stream().map(this::userToUserResponse).collect(Collectors.toList());
    }
*/

    public List<User> getLikesByPostPaginate(Post post, Integer page, Integer size) {
        return userRepository.findUsersByLikedPosts(
                post,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstName", "lastName"))
        );
    }


    public List<User> getLikesByCommentPaginate(Comment comment, Integer page, Integer size) {
        return userRepository.findUsersByLikedComments(
                comment,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstName", "lastName"))
        );
    }

    private UserResponse userToUserResponse(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return UserResponse.builder()
                .user(user)
                .followedByAuthUser(user.getFollowerUsers().contains(username))
                .build();
    }
    public final Optional<User> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String authUsername = authentication.getName(); // Obtenez le nom d'utilisateur de l'authentification
            return userRepository.findByUsername(authUsername);
        } else {
            // Gérer le cas où aucun utilisateur n'est authentifié
            return null;
        }
    }

}

