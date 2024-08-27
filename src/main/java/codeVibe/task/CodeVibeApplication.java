package codeVibe.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import codeVibe.task.repository.UserDAO;
import codeVibe.task.service.Services;
import org.springframework.beans.factory.annotation.Value;

import codeVibe.task.model.Migration;
import codeVibe.task.repository.UserDAO;
import codeVibe.task.seeders.RunSeeder;

@SpringBootApplication
public class CodeVibeApplication {
	@Value("${spring.datasource.url}")
	private String connectionString;

	@Value("${spring.datasource.username}")
	private String connectionUsername;

	@Value("${spring.datasource.password}")
	private String connectionPassword;

	// @Bean
	// public AuthenticationManager noopAuthenticationManager() {
	// 		return authentication -> {
	// 				throw new AuthenticationServiceException("Authentication is disabled");
	// 		};
	// }

	// @Bean
	// public UserDAO userDAO() {
	// 		return new UserDAO();
	// }

	// @Bean
	// public UserService userService(UserDAO userDAO) {
  //       return new UserService(userDAO);
  // }

	public static void main(String[] args) {
		SpringApplication.run(CodeVibeApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(Migration migration, RunSeeder runSeeder, Services service) {
		return args -> {
			migration.createTables();
			runSeeder.insertSeeds();
			service.performServiceTasks();
		};
	}


}
