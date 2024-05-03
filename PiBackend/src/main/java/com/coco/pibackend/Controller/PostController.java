package com.coco.pibackend.Controller;

import com.coco.pibackend.Entity.Post;
import com.coco.pibackend.Repo.PostRepository;
<<<<<<< HEAD
import com.coco.pibackend.ServiceIMp.Postservice;
import lombok.AllArgsConstructor;
=======
import com.coco.pibackend.Service.IPostservice;
import lombok.RequiredArgsConstructor;
>>>>>>> developer
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
<<<<<<< HEAD
=======

import java.io.IOException;
import java.util.List;
>>>>>>> developer

import java.io.IOException;
import java.util.List;
@CrossOrigin("*")
@RestController
<<<<<<< HEAD
@AllArgsConstructor
@RequestMapping("post")
public class PostController {
    @Autowired
    Postservice postservice;
    @Autowired
    PostRepository postRepository;
    @PostMapping("/addPOST")
    public  Post addpost(@RequestBody Post post){return postservice.addPost(post);}
=======
@RequiredArgsConstructor
@CrossOrigin("**")
@RequestMapping("post")
public class PostController {
    @Autowired
    IPostservice postservice;
    @Autowired
    PostRepository postRepository;
    @PostMapping("/addPOST")
    public Post addpost(@RequestBody Post post){return postservice.addPost(post);}

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
    /*
    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;
    private final TagService tagService;
>>>>>>> developer

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

<<<<<<< HEAD
}
=======
    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<?> likePost(@PathVariable("postId") Long postId) {
        postService.likePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/posts/{postId}/unlike")
    public ResponseEntity<?> unlikePost(@PathVariable("postId") Long postId) {
        postService.unlikePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<?> getPostComments(@PathVariable("postId") Long postId,
                                             @RequestParam("page") Integer page,
                                             @RequestParam("size") Integer size) {
        page = page < 0 ? 0 : page-1;
        size = size <= 0 ? 5 : size;
        Post targetPost = postService.getPostById(postId);
        List<CommentResponse> postCommentResponseList = commentService.getPostCommentsPaginate(targetPost, page, size);
        return new ResponseEntity<>(postCommentResponseList, HttpStatus.OK);
    }

        @PostMapping("/posts/{postId}/comments/create")
        public ResponseEntity<?> createPostComment(@PathVariable("postId") Long postId,
                                                   @RequestParam(value = "content") String content) {
            Comment savedComment = postService.createPostComment(postId, content);
            CommentResponse commentResponse = CommentResponse.builder()
                    .comment(savedComment)
                    .likedByAuthUser(false)
                    .build();
            return new ResponseEntity<>(commentResponse, HttpStatus.OK);
        }

    @PostMapping("/posts/{postId}/comments/{commentId}/update")
    public ResponseEntity<?> updatePostComment(@PathVariable("commentId") Long commentId,
                                               @PathVariable("postId") Long postId,
                                               @RequestParam(value = "content") String content) {
        Comment savedComment = postService.updatePostComment(commentId, postId, content);
        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @PostMapping("/posts/{postId}/comments/{commentId}/delete")
    public ResponseEntity<?> deletePostComment(@PathVariable("commentId") Long commentId,
                                               @PathVariable("postId") Long postId) {
        postService.deletePostComment(commentId, postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/posts/comments/{commentId}/like")
    public ResponseEntity<?> likePostComment(@PathVariable("commentId") Long commentId) {
        commentService.likeComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/posts/comments/{commentId}/unlike")
    public ResponseEntity<?> unlikePostComment(@PathVariable("commentId") Long commentId) {
        commentService.unlikeComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/posts/comments/{commentId}/likes")
    public ResponseEntity<?> getCommentLikeList(@PathVariable("commentId") Long commentId,
                                                @RequestParam("page") Integer page,
                                                @RequestParam("size") Integer size) {
        page = page < 0 ? 0 : page-1;
        size = size <= 0 ? 5 : size;
        Comment targetComment = commentService.getCommentById(commentId);
        List<User> commentLikes = userService.getLikesByCommentPaginate(targetComment, page, size);
        return new ResponseEntity<>(commentLikes, HttpStatus.OK);
    }

    @PostMapping("/posts/{postId}/share/create")
    public ResponseEntity<?> createPostShare(@PathVariable("postId") Long postId,
                                             @RequestParam(value = "content", required = false) Optional<String> content) {
        String contentToAdd = content.isEmpty() ? null : content.get();
        Post postShare = postService.createPostShare(contentToAdd, postId);
        return new ResponseEntity<>(postShare, HttpStatus.OK);
    }

    @PostMapping("/posts/{postShareId}/share/update")
    public ResponseEntity<?> updatePostShare(@PathVariable("postShareId") Long postShareId,
                                             @RequestParam(value = "content", required = false) Optional<String> content) {
        String contentToAdd = content.isEmpty() ? null : content.get();
        Post updatedPostShare = postService.updatePostShare(contentToAdd, postShareId);
        return new ResponseEntity<>(updatedPostShare, HttpStatus.OK);
    }

    @PostMapping("/posts/{postShareId}/share/delete")
    public ResponseEntity<?> deletePostShare(@PathVariable("postShareId") Long postShareId) {
        postService.deletePostShare(postShareId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/posts/tags/{tagName}")
    public ResponseEntity<?> getPostsByTag(@PathVariable("tagName") String tagName,
                                           @RequestParam("page") Integer page,
                                           @RequestParam("size") Integer size) {
        page = page < 0 ? 0 : page-1;
        size = size <= 0 ? 5 : size;
        Tag targetTag = tagService.getTagByName(tagName);
        List<PostResponse> taggedPosts = postService.getPostByTagPaginate(targetTag, page, size);
        return new ResponseEntity<>(taggedPosts, HttpStatus.OK);
    }*/

>>>>>>> developer
