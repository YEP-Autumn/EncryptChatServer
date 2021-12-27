package com.laplace.module.email;

import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @Author: YEP
 * @CreateDate: 2021/12/27 16:45
 * @Info:
 * @Email:
 */
@Component
public class MailMessenger {

    @Resource
    JavaMailSender javaMailSender;

//    public static void sendSimpleMail(JavaMailSender javaMailSender, String email) {
//        MimeMessage message = javaMailSender.createMimeMessage();
//        try {
//            //true表示需要创建一个multipart message
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom("xxx" + "<" + "xxxxxxx@qq.com" + ">");
//            helper.setTo(email);
//            helper.setSubject("subject");
//            helper.setText("content", true);
//            javaMailSender.send(message);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
