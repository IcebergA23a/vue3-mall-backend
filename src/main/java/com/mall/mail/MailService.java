package com.mall.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 使用Spring Boot 发送邮件
 * @author bingshan
 * @since 2025/12/30 11:34
 */
@Service
public class MailService {
    private Logger log = LoggerFactory.getLogger(MailService.class);

    // Spring Boot 提供了一个发送邮件的简单抽象，使用的是下面这个接口，这里直接注入即可使用
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String from;

    public void sendSimpleMail(String to, String subject, String content) {
        // 创建SimpleMailMessage 对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 邮件发送
        message.setFrom(from);
        // 邮件接送人
        message.setTo(to);
        // 邮件主题
        message.setSubject(subject);
        // 邮件内容
        message.setText(content);
        // 通过JavaMailSender类把邮件发送出去
        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
