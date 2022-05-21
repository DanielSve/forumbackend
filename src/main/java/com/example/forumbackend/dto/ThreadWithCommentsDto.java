package com.example.forumbackend.dto;

import com.example.forumbackend.model.Comment;
import com.example.forumbackend.model.LikeThread;
import com.example.forumbackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
public class ThreadWithCommentsDto {

    private Long id;

    private String title;
    private String content;

    private User user;


    private List<LikeThread> likeThreads;

    private List<Comment> comments;

    private String date;

    public ThreadWithCommentsDto() {
    }

//    public ThreadWithCommentsDto(Long id, String title, String content, User user, List<LikeThread> likeThreads, List<Comment> comments) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//        this.user = user;
//        this.likeThreads = likeThreads;
//        this.comments= comments;
//    }

    @Override
    public String toString() {
        return "ForumThread{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                '}';
    }
}
