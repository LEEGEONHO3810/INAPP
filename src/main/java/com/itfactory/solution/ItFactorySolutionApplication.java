package com.itfactory.solution;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.itfactory.solution.Mapper.POSTGRES_MES.SCR001")  // 매퍼 스캔 추가
public class ItFactorySolutionApplication extends SpringBootServletInitializer {
	
	//WAR 배포 설정
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ItFactorySolutionApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ItFactorySolutionApplication.class, args);
	}

}
