package com.example.orangeNote.user.controller;

import com.example.orangeNote.user.domain.UserDto;
import com.example.orangeNote.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepo;

    @RequestMapping
    public void test() {
        System.out.println("테스트 시작");

        UserDto usd = new UserDto();
        usd.setUser_id("tester1");
        usd.setEmail("testemail");
        usd.setPassword("testpsw");
        usd.setJoinDate(new Date());

        userRepo.save(usd);

        System.out.println("처리 완료");

    }
}
