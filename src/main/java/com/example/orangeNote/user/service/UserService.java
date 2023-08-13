package com.example.orangeNote.user.service;

import com.example.orangeNote.user.domain.UserDomain;
import com.example.orangeNote.user.dto.UserDto;
import com.example.orangeNote.user.mapper.UserMapperImpl;
import com.example.orangeNote.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public void signUp(Map<String, Object> result) {
        UserDto userDto = new UserDto();
        try {
            if (result.get("passwordConfirm").equals(true)) { // 프론트단에서 처리
                System.out.println("패스워드 일치함");

                System.out.println("입력받은 name: " + result.get("user_Name"));
                userDto.setUserName(String.valueOf(result.get("user_Name")));

                System.out.println("입력받은 password: " + result.get("user_Password"));
                userDto.setUserPassword(String.valueOf(result.get("user_Password")));

                System.out.println("입력받은 Email: " + result.get("user_Email"));
                userDto.setUserEmail(String.valueOf(result.get("user_Email")));
                UserDomain usd = UserMapperImpl.INSTANCE.dtoToDomain(userDto);
                userRepo.save(usd);
            } else {
                System.out.println("패스워드 불일치");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signIn(Map<String, Object> result) {
        try {
            UserDto userDto = new UserDto();
            userDto.setUserName(String.valueOf(result.get("userId")));
            userDto.setUserPassword(String.valueOf(result.get("userPassword")));

            if (userRepo.findUserByUserName(userDto.getUserName()) == null) {
                System.out.println("해당하는 id가 없습니다");
            } else if (userRepo.findUserByUserName(userDto.getUserName()) != userRepo.findUserByUserPassword(userDto.getUserPassword()) ) {
                System.out.println("비밀번호가 틀립니다");
            } else {
                System.out.println("로그인 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void findOneUser(Map<String, Object> map) {
        String name = String.valueOf(map.get("userName"));
        UserDomain usd = userRepo.findUserByUserName("name");
        UserDto userdto = UserMapperImpl.INSTANCE.domainToDtoSafe(usd);

    }

}
