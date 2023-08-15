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

    @RequestMapping("/")
    public void home() {
        System.out.println("시작화면");
    }

    @RequestMapping("/sample")
    public void sample() {
        List<UserDomain> list = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            UserDomain user = new UserDomain();
            user.setUserId("tester" + i);
            user.setUserPassword("pwd" + i);
            user.setUserEmail("testEmail" + i);
            list.add(user);
        }
        userService.putData(list);
    }

    @ResponseBody
    @RequestMapping("/idCheck")
    public ResponseEntity<?> idCheck(@RequestBody Map<String, Object> input) {
        Map<String, Object> response = new HashMap<>(input);

        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @RequestMapping("/emailCheck")
    public ResponseEntity<?> emailCheck(@RequestBody Map<String, Object> input) {
        Map<String, Object> response = new HashMap<>(input);

        return ResponseEntity.ok(response);
    }
    @ResponseBody
    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String signUp(@RequestBody Map<String, Object> input) {
        System.out.println("회원가입 시작");
        Map<String, Object> temp = new HashMap<>(input);
        String signUpRes = userService.signUp(temp);
        return signUpRes;
    }
    @ResponseBody
    @PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signIn(@RequestBody Map<String, Object> input)
    {
        System.out.println("로그인 시도");
        Map<String, Object> temp = new HashMap<>(input);
        Map<String, Object> response = userService.signIn(temp);
        return ResponseEntity.ok(response);
    }


}
