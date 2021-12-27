package com.laplace.module.email;

import com.laplace.module.bean.Email;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * @Author: YEP
 * @CreateDate: 2021/12/27 11:38
 * @Info:
 * @Email:
 */
@Component
@Slf4j
public class EmailMessenger {

    @Value("${messenger.email}")
    String email;

    @Value("${messenger.token}")
    String token;

    Transport ts;

    Session session;


    /**
     * 获取验证码
     *
     * @return
     */
    private String getVerificationCode() {
        //  获取6为随机验证码
        String[] letters = new String[]{
                "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m",
                "A", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String stringBuilder = "";
        for (int i = 0; i < 6; i++) {
            stringBuilder = stringBuilder + letters[(int) Math.floor(Math.random() * letters.length)];
        }
        return stringBuilder;
    }

    /**
     * 创建邮件
     *
     * @throws Exception
     */
    public MimeMessage createEmail(Email email) throws Exception {
        if (session == null) {
            if (!awakeningMessenger()) {
                log.error("唤醒信使失败,结束此次进程");
                return null;
            }
        }
        // 创建邮件
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email.getForm()));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getReceive()));
        message.setSubject(email.getSubject());
        message.setContent(email.getMultipart());
        return message;
    }


    /**
     * 唤醒信使
     */
    public boolean awakeningMessenger() {
        Properties prop = new Properties();
        try {
            // 开启debug调试，以便在控制台查看
            prop.setProperty("mail.debug", "true");
            // 设置邮件服务器主机名
            prop.setProperty("mail.host", "smtp.qq.com");
            // 发送服务器需要身份验证
            prop.setProperty("mail.smtp.auth", "true");
            // 发送邮件协议名称
            prop.setProperty("mail.transport.protocol", "smtp");
            // 开启SSL加密，否则会失败
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);
            // 创建session
            session = Session.getInstance(prop);
            // 通过session得到transport对象
            Transport ts = session.getTransport();
            // 连接邮件服务器：邮箱类型，帐号，POP3/SMTP协议授权码 163使用：smtp.163.com
            ts.connect("smtp.qq.com", email, token);
            return true;
        } catch (Exception e) {
            log.error("唤醒邮件信使失败");
            ts = null;
        }
        return false;
    }

}
