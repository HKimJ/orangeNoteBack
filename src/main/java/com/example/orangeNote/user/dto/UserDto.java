package com.example.orangeNote.user.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userId;
    private String userPassword;
    private String userEmail;
    private String userRole; // 추후 security 적용시 다시 보기

}
