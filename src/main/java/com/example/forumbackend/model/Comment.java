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

    @Column(columnDefinition="TEXT")
    private String content;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private ForumThread forumThread;

    @OneToMany
    @JoinColumn
    private List<LikeComment> likeComments;

    public Comment(String content, User user, ForumThread forumThread) {
        this.content = content;
        this.user = user;
        this.forumThread = forumThread;
    }

    public void addLikeComment(LikeComment likeComment) {
        likeComments.add(likeComment);
    }
}