package com.tedu.petCommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class PetCommunityApplication {
	public static void main(String[] args) {

		SpringApplication.run(PetCommunityApplication.class, args);
		// 2019-11-16 阳昊
		System.out.println("测试一次提交");
	}

}
