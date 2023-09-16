package com.example.takeoutorder;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("com.example.takeoutorder.mapper")
@EnableCaching //开启注解缓存功能
public class TakeOutOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TakeOutOrderApplication.class, args);
		//slf的日志信息
		log.info("项目启动成功");
	}

}
