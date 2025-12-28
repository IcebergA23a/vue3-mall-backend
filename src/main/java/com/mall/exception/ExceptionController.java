package com.mall.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

}
