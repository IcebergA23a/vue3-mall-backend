package com.mall.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Spring Task: Spring 3.0 以后自带的Task, 轻量级的Quartz, 使用起来比Quartz简单
 * @author bingshan
 * @since 2025/12/30 10:39
 */
@Component
public class Task {
    private Logger log = LoggerFactory.getLogger(Task.class);

    @Scheduled(cron = "0 0/1 * * * ?")
    public void testOne() {
        log.info("每分钟执行一次");
    }

    @Scheduled(fixedDelay = 30000)
    public void testTwo() {
        log.info("每30s执行一次");
    }

    // 每天凌晨1点执行
    @Scheduled(cron = "0 0  1 * * ?")
    public void initTask() {
        //执行任务
        log.info("执行任务" + new Date());
    }
}
