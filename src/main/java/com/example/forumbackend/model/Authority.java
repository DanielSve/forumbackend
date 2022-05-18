package com.example.forumbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "authority")
@NoArgsConstructor
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;
    private String levelOfAuthority;

    @Override
    public String getAuthority() {
        return levelOfAuthority;
    }

    public Authority(String levelOfAuthority) {
        this.levelOfAuthority = levelOfAuthority;
    }
}

