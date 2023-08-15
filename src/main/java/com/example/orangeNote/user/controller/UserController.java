package com.example.orangeNote.user.controller;

import com.example.orangeNote.user.domain.UserDomain;
import com.example.orangeNote.user.dto.MailDto;
import com.example.orangeNote.user.service.EmailService;
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
    private final EmailService emailService;

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
    @PostMapping(value = "/signUp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String signUp(@RequestBody Map<String, Object> input) {
        System.out.println("회원가입 시작");
        Map<String, Object> temp = new HashMap<>(input);
        String signUpRes = userService.signUp(temp);
        return signUpRes;
    }

    @ResponseBody
    @RequestMapping("/idCheck")
    public ResponseEntity<?> idCheck(@RequestBody Map<String, String> inputId) {
        String id = inputId.get("id");
        Map<String, String> response = userService.idCheck(id);
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @RequestMapping("/emailCheck")
    public ResponseEntity<Map<String, String>> emailCheck(@RequestBody Map<String, String> inputEmail) {
        String email = inputEmail.get("email");
        Map<String, String> response = userService.emailCheck(email); // 여기서 메일 유효하면 발송까지 함

        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @RequestMapping("/emailConfirm")
    public ResponseEntity<Map<String, String>> emailConfirm(@RequestBody Map<String, String> inputCode) {
        String verifyCode = inputCode.get("emailConfirm");
        Map<String, String> response = userService.emailConfirm(verifyCode);

        emailService.sendVerificationMail(verifyCode);

        return ResponseEntity.ok(response);
    }


    @ResponseBody
    @PostMapping(value = "/signIn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signIn(@RequestBody Map<String, Object> input)
    {
        System.out.println("로그인 시도");
        Map<String, Object> temp = new HashMap<>(input);
        Map<String, Object> response = userService.signIn(temp);
        return ResponseEntity.ok(response);
    }


}
