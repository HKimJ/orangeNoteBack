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

    private final EmailService emailService;
    private UserDomain userDomain;

    public void putData(List<UserDomain> sample) {
        userRepo.saveAll(sample);
    }
    public String signUp(Map<String, Object> temp) {
        try {
            UserDto userDto = new UserDto();
            System.out.println("입력받은 name: " + temp.get("user_Name"));
            userDto.setUserId(String.valueOf(temp.get("user_Name")));

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

    public Map<String, String> idCheck(String id) {
        Map<String, String> result = new HashMap<>();
        try {
            userDomain = userRepo.findUserByUserId(id);

            if (userDomain == null) { // 중복 없는 것을 확인
                result.put("status", "200");
                result.put("data", "가입 가능한 아이디 입니다.");
            } else {
                result.put("status", "403");
                result.put("data", "이미 가입된 아이디 입니다.");
            }
            return result;


        } catch(Exception e) {
            e.printStackTrace();
            result.put("status", "500");
            result.put("data", "알 수 없는 오류가 발생했습니다.");
            return result;
        }
    }

    public Map<String, String> emailCheck(String email) {
        Map<String, String> result = new HashMap<>();
        try {
            userDomain = userRepo.findUserByUserEmail(email);

            if (userDomain == null) { // 중복 없는 것을 확인
                result.put("status", "200");
                result.put("data", "인증 코드를 발송했습니다.");
                emailService.sendVerificationMail(email);

            } else {
                result.put("status", "403");
                result.put("data", "이미 가입된 이메일 입니다.");
            }
            return result;


        } catch(Exception e) {
            e.printStackTrace();
            result.put("status", "500");
            result.put("data", "알 수 없는 오류가 발생했습니다.");
            return result;
        }
    }

    public Map<String, String> emailConfirm(String verifyCode) {
        Map<String, String> result = new HashMap<>();
        try {

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Map<String, Object> signIn(Map<String, Object> temp) {
        Map<String, Object> result = new HashMap<>();
        try {
            UserDto userDto = new UserDto();
            userDto.setUserId(String.valueOf(temp.get("id")));
            userDto.setUserPassword(String.valueOf(temp.get("password")));

//            UserDto simpleDto = userMapper.createUserDtoFromUserDomain(userRepo.findUserByUserName(userDto.getUserName()));
//            현재 의도한대로 작동하지 않음, 추후 수정해 반영 예정
            UserDomain tempDomain = userRepo.findUserByUserId(userDto.getUserId());

            if (tempDomain != null) {
                System.out.println("입력 정보와 일치하는 아이디 존재");
                result.put("success", true);
                result.put("data", userMapper.domainToDtoSafe(tempDomain)); // 기능상 에러는 없지만 불필요한 필드값을 포함해 생성됨
            } else {
                System.out.println("입력한 계정 정보가 없거나 잘못됨");
                result.put("success", false);
                result.put("errorMessage", "입력한 계정 정보가 없거나 잘못됨");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", 500);
            result.put("data", e.getMessage());
            return result;
        }
    }

//    public void findOneUser(Map<String, Object> map) {
//        String name = String.valueOf(map.get("userName"));
//        UserDomain usd = userRepo.findUserByUserName("name");
//        UserDto userDto = UserMapperImpl.INSTANCE.domainToDtoSafe(usd);
//
//    }

}
