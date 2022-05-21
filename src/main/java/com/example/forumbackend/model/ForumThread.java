package com.example.forumbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
public class ForumThread {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Column(columnDefinition="TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn
    private List<LikeThread> likeThreads;

    private String date;

    public ForumThread() {
    }

    public ForumThread(String title, String content, User user, String date) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.date = date;
    }

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
