package com.coco.pibackend.Controller;

import com.coco.pibackend.Entity.Comment;
import com.coco.pibackend.ServiceIMp.Commentservice;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    Commentservice commentservice;
    @PostMapping("/addComment")
    public Long addComment(@RequestBody Comment comment) {
        commentservice.addComment(comment);
        return comment.getId();
    }
    @GetMapping("/retrieveALLcomment")
    @ResponseBody
    public List<Comment> retrieveAllComment(){
        List<Comment> listeComment = commentservice.retrieveAllComment();
        return listeComment;
    }
    @GetMapping("/retrieveComment/{id}")
    public Comment retrieveComment(@PathVariable("id") Long id) {
        return commentservice.listComment(id);
    }
    @PutMapping("/updateComment")
    @ResponseBody
    public Comment updateComment(@RequestBody Comment comment) {
        return commentservice.updateComment(comment);
    }
    @DeleteMapping("/deleteComment/{id}")
    public void deleteComment(@PathVariable("id") long id) {
        commentservice.deleteComment(id);
    }
}
