package com.subwaytrip.app.service.impl;

import com.subwaytrip.app.service.SendMailService;
import com.subwaytrip.app.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class SendMailServiceImpl extends LoggerUtils implements SendMailService {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private String port;

    @Value("${spring.mail.username}")
    private String user;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String auth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String starttls;

    private JavaMailSender javaMailSender;

    @Autowired
    public SendMailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    @Override
    public boolean send(String email, String certCode) throws MessagingException {
        Properties props = new Properties();

        // smtp에 필요한 인증부
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.auth", auth);

        // 호스트 / 포트
        props.put("mail.smtp.host", host);
        if (port != null) {
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.socketFactory.port", port);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }

        // 인증을 포함한 메시지 만들기
        MimeMessage message = new MimeMessage(Session.getInstance(props, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        }));

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        try {
            String subject = "[랜덤지하철여행] 이메일 인증코드 입니다.";
            String content = "안녕하세요. 랜덤지하철여행 관리자입니다 :)\n\n  - 이메일 인증코드 : " + certCode + "\n\n3분 이내에 해당 인증코드를 입력해주세요!\n\n감사합니다.";

            // 2020.05.18 BH sender : group mail 로 변경
            helper.setFrom(new InternetAddress(sender, "랜덤지하철여행"));
            helper.setTo(InternetAddress.parse(email));
            helper.setSubject(subject);
            helper.setText(content, true);
            helper.setSentDate(new Date());

            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
