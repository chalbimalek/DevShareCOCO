package com.coco.pibackend.Response;

import com.coco.pibackend.Entity.Comment;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Comment comment;
    private Boolean likedByAuthUser;
}