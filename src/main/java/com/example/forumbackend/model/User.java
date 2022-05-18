package com.example.forumbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable
    private Set<Authority> authorities;

    public User() {
    }
}
