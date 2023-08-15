package com.example.orangeNote.user.service;

import com.example.orangeNote.user.dto.MailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final RedisService redisService;

    public Map<String, String> sendVerificationMail(String email) {
        String verifyCode = generateCode();
        Map<String, String> result = new HashMap<>();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        String title = "Orange Note 인증 코드";
        StringBuilder content = new StringBuilder();
        int expireTime = 10;
        content.append("<h3> 요청하신 인증 코드는 다음과 같습니다</h3>.")
                .append("<h1> [ ").append(verifyCode).append(" ]</h1>")
                .append("<h3> 인증 코드는").append(expireTime).append("분 후에 만료됩니다</h3>");

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(email); // 메일 수신자
            mimeMessageHelper.setSubject(title); // 메일 제목
            mimeMessageHelper.setText(content.toString(), true); // 메일 본문 내용, HTML 여부
            mailSender.send(mimeMessage);
            redisService.redisTemplate.opsForValue().set(email, verifyCode, expireTime, TimeUnit.MINUTES);
            result.put("status", "200");
            result.put("data", verifyCode);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "403");
            result.put("errorMessage","알 수 없는 문제가 발생했습니다. 관리자에게 문의하세요");
            return result;
        }
    }
    private String generateCode() {
        Random random = new Random();
        int code = random.nextInt(9000) + 1000; // Generates a 4-digit number between 1000 and 9999
        return String.valueOf(code);
    }

}
