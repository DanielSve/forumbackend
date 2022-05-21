package com.example.forumbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ForumThreadDto {

    private String title;
    private String content;
    private Long userId;
    private String date;

    public ForumThreadDto() {
    }
}
