package com.mutong.mhscowboy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mutong.mhscowboy.mapper")
public class MhscowboyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MhscowboyApplication.class, args);
	}

}
