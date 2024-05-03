package com.coco.pibackend.Service;

import com.coco.pibackend.Entity.Comment;

import java.util.List;

public interface ICommentservice {
    Comment listComment(Long id);
    List<Comment> retrieveAllComment();
    Comment addComment(Comment comment);
    Comment updateComment (Comment comment);
    void deleteComment(Long id );
}
