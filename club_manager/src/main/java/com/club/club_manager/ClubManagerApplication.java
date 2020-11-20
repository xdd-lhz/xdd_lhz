package com.club.club_manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableAspectJAutoProxy
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.club.club_manager.dao")
public class ClubManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubManagerApplication.class, args);
	}

}
