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

	public static void main(String[] args) {
		SpringApplication.run(EcoleBackApplication.class, args);
	}

}
