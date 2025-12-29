package com.mall.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

/**
 */
@Controller
@RequestMapping("/file/")
public class FileController {
    /*单文件上传*/
    @RequestMapping("upload")
    @ResponseBody
    public String upload (@RequestParam("file") MultipartFile file) {
        // 获取原始名字
        String fileName = file.getOriginalFilename();
        // 文件保存路径
        String filePath = "D:\\devproject\\web\\vue3-mall-backend\\file\\";
        // 文件重命名，防止重复
        fileName = filePath + UUID.randomUUID() + fileName;
        // 文件对象
        File dest = new File(fileName);
        // 判断路径是否存在，如果不存在则创建
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            // 保存到服务器中
            file.transferTo(dest);
            return "上传成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    /*多文件上传*/
    @PostMapping("/uploads")
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("files");
        MultipartFile file = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    // 获取原始名字
                    String fileName = file.getOriginalFilename();
                    // 文件保存路径
                    String filePath = "D:\\devproject\\web\\vue3-mall-backend\\file\\";
                    // 文件重命名，防止重复
                    fileName = filePath + UUID.randomUUID() + fileName;
                    // 文件对象
                    File dest = new File(fileName);
                    // 保存到服务器中
                    file.transferTo(dest);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "上传失败";
                }
            }else {
                return "上传失败";
            }
        }
        return "上传成功";
    }

    /* 文件下载 */
    @RequestMapping("download")
    public void download(HttpServletResponse response) throws Exception {
        // 文件地址，真实环境是存放在数据库中的
        String filename="a.txt";
        String filePath = "D:\\devproject\\web\\vue3-mall-backend\\src\\main\\resources"; ;
        File file = new File(filePath + "/" + filename);
        // 穿件输入对象
        FileInputStream fis = new FileInputStream(file);
        // 设置相关格式
        response.setContentType("application/force-download");
        // 设置下载后的文件名以及header
        response.setHeader("Content-Disposition", "attachment;fileName=" +filename);
        // 创建输出对象
        OutputStream os = response.getOutputStream();
        // 常规操作
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = fis.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
        fis.close();
    }

}
