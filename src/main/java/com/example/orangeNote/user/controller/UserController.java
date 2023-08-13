package com.example.orangeNote.user.controller;

import com.example.orangeNote.user.domain.UserDomain;
import com.example.orangeNote.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8080")
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
    public void signUp(/*@RequestBody Map<String, Object> map*/) {
        System.out.println("회원가입 시작");
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_Name", "signUpName");
        map.put("user_Password", "signUpPassword");
        map.put("user_Email", "signUpEmil");
        map.put("passwordConfirm", true);
        userService.signUp(map);
    }
    @ResponseBody
    @RequestMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void signIn(@RequestBody Map<String, Object> map)
    {
        System.out.println("로그인");
        Map<String, Object> result = map;
        result.put("userId", map.get("id"));
        result.put("userPassword", map.get("password"));

        userService.signIn(result);
    }

}
