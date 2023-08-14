package com.example.orangeNote.user.service;

import com.example.orangeNote.user.domain.UserDomain;
import com.example.orangeNote.user.dto.UserDto;
import com.example.orangeNote.user.mapper.UserMapper;
import com.example.orangeNote.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private UserDomain userDomain;
    public void putData(List<UserDomain> sample) {
        userRepo.saveAll(sample);
    }
    public String signUp(Map<String, Object> temp) {
        try {
            UserDto userDto = new UserDto();
            System.out.println("입력받은 name: " + temp.get("user_Name"));
            userDto.setUserName(String.valueOf(temp.get("user_Name")));

            System.out.println("입력받은 password: " + temp.get("user_Password"));
            userDto.setUserPassword(String.valueOf(temp.get("user_Password")));

            System.out.println("입력받은 Email: " + temp.get("user_Email"));
            userDto.setUserEmail(String.valueOf(temp.get("user_Email")));
            UserDomain usd = userMapper.dtoToDomain(userDto);
            userRepo.save(usd);
            return "회원가입에 성공했습니다.";
        } catch (Exception e) {
            e.printStackTrace();
            return "회원가입 과정에서 문제가 발생하였습니다.";
        }
    }

    public String signUpCheck(String input) {
        String result;
        try {
            if (input.contains("@")) { // 아이디에 특수문자 사용 못하게 해야함(특히 @)
                userDomain = userRepo.findUserByUserEmail(input);
            } else {
                userDomain = userRepo.findUserByUserName(input);
            }
            result = userDomain != null? "ok" : "fail";
            return result;

        } catch(Exception e) {
            e.printStackTrace();
            result = "500";
            return result;
        }
    }

    public Map<String, Object> signIn(Map<String, Object> temp) {
        Map<String, Object> result = new HashMap<>();
        try {
            UserDto userDto = new UserDto();
            userDto.setUserName(String.valueOf(temp.get("id")));
            userDto.setUserPassword(String.valueOf(temp.get("password")));

//            UserDto simpleDto = userMapper.createUserDtoFromUserDomain(userRepo.findUserByUserName(userDto.getUserName()));
//            현재 의도한대로 작동하지 않음, 추후 수정해 반영 예정
            UserDomain tempDomain = userRepo.findUserByUserName(userDto.getUserName());

            if (tempDomain.getUserName().equals(temp.get("id")) && tempDomain.getUserPassword().equals(temp.get("password"))) {
                System.out.println("입력 정보와 일치하는 아이디 존재");
                result.put("status", 200);
                result.put("data", userMapper.domainToDtoSafe(tempDomain)); // 기능상 에러는 없지만 불필요한 필드값을 포함해 생성됨
            } else {
                System.out.println("입력한 계정 정보가 없거나 잘못됨");
                result.put("status", 404);
                result.put("data", "입력한 계정 정보가 없거나 잘못됨");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", 500);
            result.put("data", e.getMessage());
            return result;
        }
    }

    public void findOneUser(Map<String, Object> map) {
//        String name = String.valueOf(map.get("userName"));
//        UserDomain usd = userRepo.findUserByUserName("name");
//        UserDto userDto = UserMapperImpl.INSTANCE.domainToDtoSafe(usd);

    }

}
