package com.example.forumbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name="user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String username;
    private String password;

//    @OneToMany
//    @JoinColumn
//    private List<Thread> threads;
//
//    @OneToMany
//    @JoinColumn
//    private List<Comment> comments;

    public User() {
    }
}
