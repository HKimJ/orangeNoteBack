package com.example.orangeNote.user.controller;

import com.example.orangeNote.user.domain.UserDomain;
import com.example.orangeNote.user.dto.UserDto;
import com.example.orangeNote.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @PostMapping("/signup")
    public void signUp(@RequestBody Map<String, Object> map)
    {
        System.out.println("회원가입 시작");
        Map<String, Object> result = map;
        result.put("userId", "testid");
        result.put("userPassword", "testPsw");

    }

    @ResponseBody
    @PostMapping("/signin")
    public void signIn(@RequestBody Map<String, Object> map)
    {
        System.out.println("로그인");
        Map<String, Object> result = map;
        result.put("userId", "testid");
        result.put("userPassword", "testPsw");

        userService.signIn(result);


    }


}
