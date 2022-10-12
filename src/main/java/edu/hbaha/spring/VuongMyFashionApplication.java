package edu.hbaha.spring;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.ApplicationScope;

import edu.hbaha.spring.config.StorageProperties;
import edu.hbaha.spring.models.CategoryDto;
import edu.hbaha.spring.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class VuongMyFashionApplication {

	public static void main(String[] args) {
		SpringApplication.run(VuongMyFashionApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args -> {
			storageService.init();
		});
	}
	
}
