package com.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.community.mapper")
@SpringBootApplication
public class CommunityManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityManageApplication.class, args);
	}

}
