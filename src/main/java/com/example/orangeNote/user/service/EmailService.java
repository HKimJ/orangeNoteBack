//package com.example.orangeNote.user.service;
//
//import com.example.orangeNote.user.dto.MailDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.util.Random;
//
//@Service
//@RequiredArgsConstructor
//public class EmailService {
//
//    private final JavaMailSender mailSender;
//
//    public String sendMail(MailDto mailDto, String type) {
//        String authNum = createCode();
//
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//
//        if (type.equals("password")) userService.SetTempPassword(emailMessage.getTo(), authNum);
//
//        try {
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
//            mimeMessageHelper.setTo(mailDto.getAddress()); // 메일 수신자
//            mimeMessageHelper.setSubject(mailDto.getTitle()); // 메일 제목
//            mimeMessageHelper.setText(setContext(authNum, type), true); // 메일 본문 내용, HTML 여부
//            javaMailSender.send(mimeMessage);
//
//            log.info("Success");
//
//            return authNum;
//
//        } catch (MessagingException e) {
//            log.info("fail");
//            throw new RuntimeException(e);
//        }
//    }
//
//    public String createCode() {
//        Random random = new Random();
//        StringBuffer key = new StringBuffer();
//
//        for (int i = 0; i < 8; i++) {
//            int index = random.nextInt(4);
//
//            switch (index) {
//                case 0: key.append((char) ((int) random.nextInt(26) + 97)); break;
//                case 1: key.append((char) ((int) random.nextInt(26) + 65)); break;
//                default: key.append(random.nextInt(9));
//            }
//        }
//        return key.toString();
//    };
//
//}
