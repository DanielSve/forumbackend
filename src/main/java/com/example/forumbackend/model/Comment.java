package com.example.forumbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

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

    public Comment(String content, User user) {
        this.content = content;
        this.user = user;
    }
}