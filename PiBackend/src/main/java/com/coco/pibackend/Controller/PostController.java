package com.coco.pibackend.Controller;

import com.coco.pibackend.Entity.Post;
import com.coco.pibackend.Repo.PostRepository;
import com.coco.pibackend.ServiceIMp.Postservice;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("post")
public class PostController {
    @Autowired
    Postservice postservice;
    @Autowired
    PostRepository postRepository;
    @PostMapping("/addPOST")
    public  Post addpost(@RequestBody Post post){return postservice.addPost(post);}

    @GetMapping("/retrievePost/{id}")
    public Post retrievePost(@PathVariable("id") Long id) {
        return postservice.retrievePost(id);
    }

    @PutMapping("/EditPost/{id}")
    public Post editPost(@PathVariable("id") Long id, @RequestBody Post updatedPost) {
        return postservice.updatePost(id, updatedPost);
    }



    @DeleteMapping("/deletePost/{id}")
    public void deletePost(@PathVariable("id") long id) {
        postservice.deletePost(id);
    }

    @GetMapping("/retrieveallPOST")
    public List<Post> getPosts() {
        List<Post> listposts = postservice.retrieveAllPost();
        return listposts;
    }

    @PostMapping("/AddPostFile/{id}")
    public ResponseEntity<Post> AddPostFile(@RequestParam("file") MultipartFile multipartFile,
                                               @PathVariable("id") Long id
    )
    {
        Post messsage = postservice.addFile(multipartFile,id);
        return ResponseEntity.ok().body(messsage);
    }

    @GetMapping("/retrieveFile/{id}")

    public ResponseEntity<byte[]> retrieveFile(@PathVariable("id") Long id) throws IOException {
        return postservice.retrieveFile(id);
    }

    @GetMapping("/SearchPosts/{word}")
    public List<Post> SearchPosts(@PathVariable("word") String word ){
        return postservice.SearchPosts(word);
    }

}
