package com.example.orangeNote.user.controller;

import com.example.orangeNote.user.domain.UserDomain;
import com.example.orangeNote.user.dto.UserDto;
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
    private static UserDto userDto = new UserDto();

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
            user.setUserEmail("email" + i);
            list.add(user);
        }
        userService.putData(list);
    }
    @RequestMapping("/sample2")
    public void sample2() {
        List<UserDomain> list2 = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            UserDomain user = new UserDomain();

            user.setUserPassword("pwd" + i);
            user.setUserEmail("email" + i);

            list2.add(user);
        }
        userService.putData(list2);
    }


    @ResponseBody
    @PostMapping(value = "/signUp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> signUp(@RequestBody Map<String, Object> input) {
        System.out.println("회원가입 시작");
        Map<String, Object> temp = new HashMap<>(input);
        Map<String, Object> response;
        response = userService.signUp(temp);
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @RequestMapping(value= "/idCheck", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> idCheck(@RequestBody Map<String, String> inputId) {
        String id = inputId.get("id");
        Map<String, Object> response = userService.idCheck(id);
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @RequestMapping(value ="/emailCheck", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> emailCheck(@RequestBody Map<String, String> inputEmail) {
        String email = inputEmail.get("email");
        userDto.setUserEmail(email);
        Map<String, Object> response = userService.emailCheck(email); // 여기서 메일 유효하면 발송까지 함

        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @RequestMapping(value ="/emailConfirm", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> emailConfirm(@RequestBody Map<String, String> inputCode) {
        String email = userDto.getUserEmail();
        String verifyCode = inputCode.get("emailCheckCode");
        Map<String, Object> response = userService.emailConfirm(email, verifyCode);
        return ResponseEntity.ok(response);
    }


    @ResponseBody
    @PostMapping(value = "/signIn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> signIn(@RequestBody Map<String, Object> input)
    {
        System.out.println("로그인 시도");
        Map<String, Object> temp = new HashMap<>(input);
        Map<String, Object> response = userService.signIn(temp);
        return ResponseEntity.ok(response);
    }
}
