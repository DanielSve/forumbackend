package com.example.forumbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeThreadDto {

    private Long threadId;
    private Long userId;
}
