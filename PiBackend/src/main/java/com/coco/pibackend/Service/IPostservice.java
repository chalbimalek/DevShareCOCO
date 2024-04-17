package com.coco.pibackend.Service;

import com.coco.pibackend.Entity.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IPostservice {
    Post addPost(Post post);

    List<Post> retrieveAllPost();

    void deletePost(Long id);

    Post retrievePost(Long id);

    Post addFile(MultipartFile multipartFile, Long id);

    ResponseEntity<byte[]> retrieveFile(Long id) throws IOException;



    Post updatePost(Long id, Post updatedPost);

    List<Post> SearchPosts(String word);
}
