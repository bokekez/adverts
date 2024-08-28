package codeVibe.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import codeVibe.task.model.Migration;
import codeVibe.task.seeders.RunSeeder;

@SpringBootApplication
public class CodeVibeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeVibeApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(Migration migration, RunSeeder runSeeder) {
		return args -> {
			migration.createTables();
			runSeeder.insertSeeds();
		};
	}

}
