package com.example.orangeNote.user.service;

import com.example.orangeNote.project.domain.ProjectDomain;
import com.example.orangeNote.project.repository.ProjectRepository;
import com.example.orangeNote.user.domain.UserDomain;
import com.example.orangeNote.user.dto.UserDto;
import com.example.orangeNote.user.mapper.UserMapper;
import com.example.orangeNote.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final ProjectRepository projectRepo;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    private final EmailService emailService;
    private UserDomain userDomain;

    public void putData(List<UserDomain> sample) {
        userRepo.saveAll(sample);
    }
//    public void putProject() {
//        List<UserDomain> rs = new ArrayList<>();
//
//        for (int i = 0; i < 11; i++) {
//            UserDomain user = userRepo.findUserByUserSeq(Long.valueOf(i));
//            List<ProjectDomain> input = new ArrayList<>();
//           if (i % 3 == 0) {
//               input.add(projectRepo.findProjectByProjectId(Long.valueOf(i)));
//               user.setProjects(input);
//            }
//
//        }
//    }
    public Map<String, Object> signUp(Map<String, Object> temp) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserDto userDto = new UserDto();
            System.out.println("입력받은 id: " + temp.get("id"));
            userDto.setUserId(String.valueOf(temp.get("id")));

            System.out.println("입력받은 password: " + temp.get("password"));
            userDto.setUserPassword(String.valueOf(temp.get("password")));

            System.out.println("입력받은 Email: " + temp.get("email"));
            userDto.setUserEmail(String.valueOf(temp.get("email")));
            UserDomain usd = userMapper.dtoToDomain(userDto);
            Object obj = userRepo.save(usd);
            if (obj != null) {
                response.put("success", true);
                response.put("data", "회원가입에 성공했습니다.");
            } else {
                response.put("success", false);
                response.put("data", "회원가입에 실패했습니다.");
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("errorMessage", "회원가입 과정에서 문제가 발생하였습니다. 관리자에게 문의해주세요");
            return response;
        }
    }

    public Map<String, Object> idCheck(String id) {
        Map<String, Object> result = new HashMap<>();
        try {
            userDomain = userRepo.findUserByUserId(id);

            if (userDomain == null) { // 중복 없는 것을 확인
                result.put("success", true);
                result.put("data", "가입 가능한 아이디 입니다.");
            } else {
                result.put("success", false);
                result.put("data", "이미 가입된 아이디 입니다.");
            }
            return result;


        } catch(Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("errorMessage", "알 수 없는 오류가 발생했습니다.");
            return result;
        }
    }

    public Map<String, Object> emailCheck(String email) {
        Map<String, Object> result = new HashMap<>();
        try {
            userDomain = userRepo.findUserByUserEmail(email);

            if (userDomain == null) { // 중복 없는 것을 확인
                result.put("success", true);
                result.put("data", "인증 코드를 발송했습니다.");
                emailService.sendVerificationMail(email);

            } else {
                result.put("success", false);
                result.put("data", "이미 가입된 이메일 입니다.");
            }
            return result;


        } catch(Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("errorMessage", "알 수 없는 오류가 발생했습니다.");
            return result;
        }
    }

    public Map<String, Object> emailConfirm(String email, String verifyCode) {
        Map<String, Object>  response = new HashMap<>();
        try {
            response = emailService.confirmVerificationMail(email, verifyCode);

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("errorMessage", "인증코드를 검증하는 동안 문제가 발생했습니다. 관리자에게 문의해주세요");
            return response;
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
