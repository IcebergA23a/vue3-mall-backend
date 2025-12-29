package com.mall.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述：异常处理控制器
 */
@Controller
public class ExceptionController {
    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @RequestMapping("/exceptionMethod")
    public String exceptionMethod(Model model) {

        model.addAttribute("msg", "没有抛出异常");

        int num = 1/0;
        log.info(String.valueOf(num));

        return "index";
    }

    @RequestMapping("/exceptionNullPointer")
    public String exceptionNullPointer(Model model) {
        model.addAttribute("msg", "没有抛出异常");
        Object obj = null;
        obj.toString();
        return "index";
    }

    /**
     * 使用 @ExceptionHandler 处理局部异常
     * 描述：捕获 ExceptionController 中的 NullPointerException 异常
     * @param model 将Model对象注入到方法中
     * @param e 将产生异常对象注入到方法中
     * @return 指定错误页面
     */
     @ExceptionHandler(value = {NullPointerException.class})
     public String nullPointerExceptionHandle(Model model, Exception e) {
     model.addAttribute("msg", "@ExceptionHandler: " + e.getMessage());
     log.info(e.getMessage());
     return "error";
     }

    @RequestMapping("/exceptionSimpleMappingExceptionResolver")
    public String exceptionSimpleMappingExceptionResolver(Model model) {
        model.addAttribute("msg", "没有抛出异常");

        int[] arr = new int[3];
        arr[30] = 10;
        return "index";
    }

}
