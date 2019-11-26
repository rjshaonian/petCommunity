package com.tedu.petCommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class PetCommunityApplication {
	public static void main(String[] args) {
		SpringApplication.run(PetCommunityApplication.class, args);
		System.out.println("系统启动完毕");
	}

}
