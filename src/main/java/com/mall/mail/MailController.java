package com.mall.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试发送邮件
 * @author bingshan
 * @since 2025/12/30 12:09
 */
@Controller
@RequestMapping("/email")
public class MailController {
    /*注入发送邮件的接口*/
    @Autowired
    private MailService mailService;

    /** 测试发送文本邮件 */
    @GetMapping("/sendSimpleMail")
    @ResponseBody
    public String sendSimpleMail() {
        mailService.sendSimpleMail(
                "xxxxxxx@163.com",
                "主题：普通邮件",
                "内容：第一封纯文本邮件");
        return "success";
    }

    /** 测试发送HTML邮件 */
    @GetMapping("/sendHtmlMail")
    @ResponseBody
    public String sendHtmlMail() {
        mailService.sendHtmlMail(
                "xxxxxx@163.com",
                "主题：Html邮件",
                "<h1>内容：</h1> <br/> <h2>第一封纯文本邮件</h2>");
        return "success";
    }

    /** 测试发送带附件邮件 */
    @GetMapping("/sendAttachmentMail")
    @ResponseBody
    public String sendAttachmentMail() {
        String content = """
        <html>
            <body>
                <h3><font color=\"red\">" + "大家好，这是springboot发送的HTML邮件，带有附件哦" + "</font>
                </h3>
            </body>
        </html>
        """;
        String filePath = "E:\\C语言书籍\\第一章 C语言概述.docx";
        mailService.sendAttachmentMail(
                "xxxxxx@163.com",
                "主题：带附件邮件",
                content,
                filePath);
        return "success";
    }

}
