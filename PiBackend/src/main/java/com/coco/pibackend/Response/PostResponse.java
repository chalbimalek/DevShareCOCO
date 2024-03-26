package com.coco.pibackend.Response;

import com.coco.pibackend.Entity.Post;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Post post;
    private Boolean likedByAuthUser;
}