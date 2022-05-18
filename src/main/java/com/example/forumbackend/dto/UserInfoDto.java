package com.example.forumbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDto {

    Long id;
    String userName;
    String email;
    String token;

}
