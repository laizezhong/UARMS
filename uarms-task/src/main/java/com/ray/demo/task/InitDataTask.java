package com.ray.demo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

/** 
* @author laizezhong E-mail: laizezhong@eigpay.cn
* @version 创建时间：2017年3月17日 上午11:32:04 
*  
*/
@Component
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml,"
		+ "applicationContext-service.xml,"
		+ "applicationContext-shiro.xml,"
		+ "ehcache-local.xml" })
public class InitDataTask {
	@Scheduled(cron = "* * * * * ? ") // 间隔5秒执行
    public static void taskCycle() {
        System.out.println("使用SpringMVC框架配置定时任务");
    }
	
	public static void main(String[] args) {
		taskCycle();
	}
}
 