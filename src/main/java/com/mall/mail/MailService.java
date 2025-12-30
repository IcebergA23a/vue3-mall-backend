package com.mall.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 使用Spring Boot 发送邮件
 * @author bingshan
 * @since 2025/12/30 11:34
 */
@Service
public class MailService {
    private Logger logger = LoggerFactory.getLogger(MailService.class);

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

    public void sendHtmlMail(String to, String subject, String content) {
        // 获取 MimeMessage对象
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(message, true);
            // 邮件发送
            mimeMessageHelper.setFrom(from);
            // 邮件接送人
            mimeMessageHelper.setTo(to);
            // 邮件主题
            mimeMessageHelper.setSubject(subject);
            // 邮件内容，HTML格式
            mimeMessageHelper.setText(content, true);
            // 发送
            mailSender.send(message);
            // 日志xinx
            logger.info("邮件已经发送");
        } catch (MessagingException e) {
            logger.error("发送邮件时发生异常！", e);
            throw new RuntimeException(e);
        }
    }

    /* 带附件邮件*/
    public void sendAttachmentMail(String to, String subject, String content, String filePath) {
        logger.info("发送带附件邮件开始：{},{},{},{}", to, subject, content, filePath);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            //true代表支持多组件，如附件，图片等
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = file.getFilename();
            helper.addAttachment(fileName, file);//添加附件，可多次调用该方法添加多个附件
            mailSender.send(message);
            logger.info("发送带附件邮件成功");
        } catch (MessagingException e) {
            logger.error("发送带附件邮件失败", e);
        }
    }

    /*发送带图片的邮件*/
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {

        logger.info("发送带图片邮件开始：{},{},{},{},{}", to, subject, content, rscPath, rscId);
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);//重复使用添加多个图片
            mailSender.send(message);
            logger.info("发送带图片邮件成功");
        } catch (MessagingException e) {
            logger.error("发送带图片邮件失败", e);
        }
    }


}
