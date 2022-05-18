package com.example.forumbackend;

import com.example.forumbackend.model.Authority;
import com.example.forumbackend.repository.AuthoritiesRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ForumbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumbackendApplication.class, args);
    }

    private final AuthoritiesRepository authoritiesRepository;
    public ForumbackendApplication(AuthoritiesRepository authoritiesRepository) {
        this.authoritiesRepository = authoritiesRepository;
    }

    @PostConstruct
    public void init() {
        Authority auth1 = authoritiesRepository.getByLevelOfAuthority("Auth1").orElse(null);
        if(auth1==null) {
            auth1 = new Authority("Auth1");
            authoritiesRepository.save(auth1);
        }
    }

}
