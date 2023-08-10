package com.example.orangeNote.user.service;

import com.example.orangeNote.user.domain.UserDomain;
import com.example.orangeNote.user.dto.UserDto;
import com.example.orangeNote.user.mapper.UserMapperImpl;
import com.example.orangeNote.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
//    private UserDomain userDomain;

    public void putData(List<UserDomain> sample) {

        userRepo.saveAll(sample);

    }
//        System.out.println("서비스 단 시작");
//        System.out.println("input username: " + input.getUserName());
//        System.out.println("input userpassword: " + input.getUserPassword());
//        System.out.println("input useremail: " + input.getUserEmail());
//        UserDto usd = new UserDto();
//        usd.setUserName(input.getUserName());
//        System.out.println("Dto에 들어간 이름: "+ usd.getUserName());
//        usd.setUserPassword(input.getUserPassword());
//        System.out.println("Dto에 들어간 비번: "+ usd.getUserPassword());
//        usd.setUserEmail(input.getUserEmail());
//        System.out.println("Dto에 들어간 이메일: "+ usd.getUserEmail());
//        userRepo.save(UserMapperImpl.INSTANCE.dtoToDomain(usd));
//    }

    public void signUp(Map<String, Object> result) {
        UserDto userDto = new UserDto();
        try {
            if (result.get("passwordConfirm").equals(true)) {

                userDto.setUserName(String.valueOf(result.get("user_Name")));
                userDto.setUserPassword(String.valueOf(result.get("user_password")));
                userDto.setUserEmail(String.valueOf(result.get("user_email")));
                userRepo.save(UserMapperImpl.INSTANCE.dtoToDomain(userDto));
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signIn(Map<String, Object> result) {
        try {
            UserDto userDto = new UserDto();
            userDto.setUserName(String.valueOf(result.get("userid")));
            userDto.setUserPassword(String.valueOf(result.get("userPassword")));

            if (userRepo.findUserByUserName(userDto.getUserName()) == null) {
                System.out.println("해당하는 id가 없습니다");
            } else if (userRepo.findUserByUserPassword(userDto.getUserPassword()) == null) {
                System.out.println("비밀번호가 틀립니다");
            } else {
                System.out.println("로그인 성공");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
