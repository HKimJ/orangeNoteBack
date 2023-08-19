package com.example.orangeNote.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final RedisService redisService;
    private final int EXPIRE_MIN = 3;
    public Map<String, Object> sendVerificationMail(String email) {
        String verifyCode = generateCode();
        Map<String, Object> response = new HashMap<>();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        String title = "Orange Note 인증 코드";
        StringBuilder content = new StringBuilder();

        content.append("<h3> 요청하신 인증 코드는 다음과 같습니다.</h3>")
                .append("<h1> [ ").append(verifyCode).append(" ]</h1>")
                .append("<h3> 인증 코드는 ").append(EXPIRE_MIN).append("분 후에 만료됩니다.</h3>");

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(email); // 메일 수신자
            mimeMessageHelper.setSubject(title); // 메일 제목
            mimeMessageHelper.setText(content.toString(), true); // 메일 본문 내용, HTML 여부
            mailSender.send(mimeMessage);
            redisService.storeDataInRedis(email, verifyCode, EXPIRE_MIN);
            response.put("success", "200");
            response.put("data", true);
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("data","알 수 없는 문제가 발생했습니다. 관리자에게 문의하세요");
            return response;
        }
    }

    public Map<String, Object> confirmVerificationMail(String email, String verifyCode) {
        Map<String, Object> response = new HashMap<>();
        try {
            String storedCode = redisService.retrieveDataFromRedis(email);
            if (storedCode.equals(verifyCode)) {
                response.put("success", true);
                response.put("data", "인증 완료");
            } else {
                response.put("success", false);
                response.put("data", "코드가 일치하지 않습니다.");
            }
            return response;
       } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("data", "인증 코드가 만료되었거나 인증 과정에서 문제가 발생했습니다.");
            return response;
        }
    }
    private String generateCode() {
        Random random = new Random();
        int code = random.nextInt(9000) + 1000; // Generates a 4-digit number between 1000 and 9999
        return String.valueOf(code);
    }

}
