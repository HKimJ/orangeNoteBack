package com.example.orangeNote.user.controller;

import com.example.orangeNote.user.domain.UserDomain;
import com.example.orangeNote.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping
    public void home() {
        System.out.println("시작화면");
    }

    @RequestMapping("/sample")
    public void sample() {
        List<UserDomain> list = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            UserDomain user = new UserDomain();
            user.setUserName("tester" + i);
            user.setUserPassword("testPwd" + i);
            user.setUserEmail("testEmail" + i);
            list.add(user);
        }
        userService.putData(list);
    }
    @ResponseBody
    @RequestMapping("/signup")
    public void signUp(@RequestBody Map<String, Object> input) {
        System.out.println("회원가입 시작");
        Map<String, Object> temp = new HashMap<>(input);
    }
    @ResponseBody
    @RequestMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signIn(@RequestBody Map<String, Object> map)
    {
        System.out.println("로그인 시도");
        Map<String, Object> input = new HashMap<>(map);
        Map<String, Object> response = userService.signIn(input);
        return ResponseEntity.ok(response);
    }


}
