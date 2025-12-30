package com.mall.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * 测试发送邮件
 * @author bingshan
 * @since 2025/12/30 12:09
 */
@Controller
@RequestMapping("/email")
public class MailController {
    /* 注入发送邮件的接口 */
    @Autowired
    private MailService mailService;

    /* TemplateEngine来对模板进行渲染 */
    @Autowired
    private TemplateEngine templateEngine;

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
                <h3><font color="red">大家好，这是springboot发送的HTML邮件，带有附件哦</font>
                </h3>
            </body>
        </html>
        """;
        String filePath = "D:\\devproject\\web\\vue3-mall-backend\\file\\ef3689.png";
        mailService.sendAttachmentMail(
                "xxxxxx@163.com",
                "主题：带附件邮件",
                content,
                filePath);
        return "success";
    }

    /** 测试发送带图片邮件 */
    @GetMapping("/sendInlineResourceMail")
    @ResponseBody
    public String sendInlineResourceMail() {
        String rscPath = "D:\\devproject\\web\\vue3-mall-backend\\file\\mePicture.jpg";
        String rscId = "001";
        /*使用cid标注出静态资源*/
        String content = """
                <html>
                    <body>
                        <h3>
                            <font color="red">大家好，这是springboot发送的HTML邮件，有图片哦</font>
                        </h3>
                        <img src='cid:001'>
                    </body>
                </html>
                """;
        mailService.sendInlineResourceMail(
                "xxxxxx@163.com",
                "主题：带附件邮件",
                content,
                rscPath,
                rscId);
        return "success";
    }

    /**
     * 指定模板发送邮件
     */
    @GetMapping("/sendTemplateMail")
    @ResponseBody
    public void sendTemplateMail() {
        //向Thymeleaf模板传值，并解析成字符串
        Context context = new Context();
        context.setVariable("projectName", "vue3-mall-backend");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("xxxxxx@163.com", "这是一个模板文件", emailContent);
    }

}
