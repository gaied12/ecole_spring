package com.example.Ecoleback;

import com.example.Ecoleback.Util.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties({
		FileUploadProperties.class
})


public class EcoleBackApplication {
	 /*   ZonedDateTime now = ZonedDateTime.of(LocalDateTime.of(2021,01,01,13,00), ZoneId.systemDefault());
        System.out.printf("Week %d%n", now.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));*/

	public static void main(String[] args) {
		SpringApplication.run(EcoleBackApplication.class, args);
	}

}
