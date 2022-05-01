package com.example.forumbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {

    Long threadId;
    Long userId;
    String content;
}
