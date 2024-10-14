package Adverts.task.database.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import codeVibe.task.seeders.AdvertsSeeder;
import codeVibe.task.seeders.UsersSeeder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class RunSeeder {

    @Value("${spring.datasource.url}")
    private String connectionString;

    @Value("${spring.datasource.username}")
    private String connectionUsername;

    @Value("${spring.datasource.password}")
    private String connectionPassword;

    @Autowired
    private AdvertsSeeder insertAdverts;

    @Autowired
    private UsersSeeder insertUsers;

    public void insertSeeds() {
        try (Connection connection = DriverManager.getConnection(connectionString, connectionUsername,
                connectionPassword);
                Statement statement = connection.createStatement()) {

            ResultSet checkSeed = statement.executeQuery("SELECT * FROM adverts, users");

            if (!checkSeed.next()) {
                statement.execute(insertAdverts.getSqlScript());
                statement.execute(insertUsers.getSqlScript());
                System.out.println("Seeding successful");
            } else {
                System.out.println("Database is already seeded.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Seeding failed: " + e.getMessage());
        }
    }

}
