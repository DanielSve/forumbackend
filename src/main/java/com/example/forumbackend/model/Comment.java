package com.example.forumbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "comment")
@Data
@AllArgsConstructor
public class Comment {

    public Comment() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn
    private User user;

    @OneToMany
    @JoinColumn
    private List<LikeComment> likeComments;

    public Comment(String content, User user) {
        this.content = content;
        this.user = user;
    }

    public void addLikeComment(LikeComment likeComment) {
        likeComments.add(likeComment);
    }
}