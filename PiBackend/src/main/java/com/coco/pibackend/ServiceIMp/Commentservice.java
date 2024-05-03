package com.coco.pibackend.ServiceIMp;

import com.coco.pibackend.Entity.Comment;
import com.coco.pibackend.Repo.CommentRepository;
import com.coco.pibackend.Service.ICommentservice;
import com.coco.pibackend.Repo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Commentservice implements ICommentservice {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment listComment(Long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public List<Comment> retrieveAllComment() {
        List<Comment> listC = (List<Comment>) commentRepository.findAll();
        return listC;
    }

    @Override
    public Comment updateComment(Comment comment) {
        if (commentRepository.existsById(comment.getId())) {
            Comment c = commentRepository.findById(comment.getId()).get();
            c.setText(comment.getText());
            c.setReact(comment.getReact());
            c.setDate(comment.getDate());
            commentRepository.save(comment);
        }
        return comment;
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
