package com.example.forumbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeCommentDto {

    private Long commentId;
    private Long userId;
}
