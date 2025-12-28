package com.mall.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 使用 @ControllerAdvice 处理全局异常
 */
@ControllerAdvice
public class GlobalException {
    private static final Logger log = LoggerFactory.getLogger(GlobalException.class);

    /**
     * 描述：捕获 ArithmeticException 异常
     * @param model 将Model对象注入到方法中
     * @param e 将产生异常对象注入到方法中
     * @return 指定错误页面
     */
    @ExceptionHandler(value = {ArithmeticException.class})
    public String arithmeticExceptionHandle(Model model, Exception e) {
        model.addAttribute("msg",
                "@ControllerAdvice + @ExceptionHandler : " + e.getMessage());
        log.info(e.getMessage());
        return "error";
    }

}
