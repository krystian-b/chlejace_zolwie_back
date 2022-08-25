package com.back.chlejacezolwie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@EnableJdbcHttpSession
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
public class ChlejacezolwieApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChlejacezolwieApplication.class, args);
	}

}
