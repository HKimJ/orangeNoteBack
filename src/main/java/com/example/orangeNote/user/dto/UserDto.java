package com.example.orangeNote.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userName;
    private String userPassword;
    private String userEmail;

    private String userRole; // 추후 security 적용시 빼기

    public UserDto(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }
    public UserDto(String userName, String userPassword, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

}
