package miu.edu.Lab10PartC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class Lab10PartCApplication implements CommandLineRunner {

	@Autowired
	AppConfig appConfig;

	@Autowired
	UserConfig userConfig;

	public static void main(String[] args) {
		SpringApplication.run(Lab10PartCApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application Name: " + appConfig.getName());
		System.out.println("Version: " + appConfig.getVersion());
		System.out.println("Server URL: " + appConfig.getServerUrl());
		System.out.println("Server Name: " + appConfig.getServerName());

		System.out.println("User Firstname: " + userConfig.getFirstname());
		System.out.println("User Lastname: " + userConfig.getLastname());
		System.out.println("Username: " + userConfig.getUsername());
		System.out.println("Password: " + userConfig.getPassword());

		System.out.println("Countries:");
		appConfig.getCountries().forEach(country -> System.out.println(" - " + country));
	}
}
