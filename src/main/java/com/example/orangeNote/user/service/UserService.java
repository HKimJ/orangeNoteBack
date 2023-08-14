package com.example.orangeNote.user.service;

import com.example.orangeNote.user.domain.UserDomain;
import com.example.orangeNote.user.dto.UserDto;
import com.example.orangeNote.user.mapper.UserMapper;
import com.example.orangeNote.user.mapper.UserMapperImpl;
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

    public Map<String, Object> signIn(Map<String, Object> input) {
        try {
            UserDto userDto = new UserDto();
            userDto.setUserName(String.valueOf(input.get("id")));
            userDto.setUserPassword(String.valueOf(input.get("password")));

//            UserDto simpleDto = userMapper.createUserDtoFromUserDomain(userRepo.findUserByUserName(userDto.getUserName()));
//            현재 의도한대로 작동하지 않음, 추후 수정해 반영 예정
            UserDomain temp = userRepo.findUserByUserName(userDto.getUserName());
            Map<String, Object> result = new HashMap<>();
            if (temp.getUserName().equals(input.get("id")) && temp.getUserPassword().equals(input.get("password"))) {
                System.out.println("입력 정보와 일치하는 아이디 존재");
                result.put("status", 200);
                result.put("data", userMapper.domainToDtoSafe(temp)); // 기능상 에러는 없지만 불필요한 필드값을 포함해 생성됨
            } else {
                System.out.println("입력한 계정 정보가 없거나 잘못됨");
                result.put("status", 404);
                result.put("data", "입력한 계정 정보가 없거나 잘못됨");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void findOneUser(Map<String, Object> map) {
//        String name = String.valueOf(map.get("userName"));
//        UserDomain usd = userRepo.findUserByUserName("name");
//        UserDto userDto = UserMapperImpl.INSTANCE.domainToDtoSafe(usd);

    }

}
