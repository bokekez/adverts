package Adverts.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Adverts.task.model.Migration;
import Adverts.task.seeders.RunSeeder;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class AdvertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvertsApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(Migration migration, RunSeeder runSeeder) {
		return args -> {
			migration.createTables();
			runSeeder.insertSeeds();
		};
	}

}
