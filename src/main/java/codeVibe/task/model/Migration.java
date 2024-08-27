package codeVibe.task.model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Component
public class Migration {

    @Value("${spring.datasource.url}")
    private String connectionString;

    @Value("${spring.datasource.username}")
    private String connectionUsername;

    @Value("${spring.datasource.password}")
    private String connectionPassword;

    @Autowired
    private Users usersTable;

    @Autowired
    private Adverts advertsTable;

    public void createTables() {

    try (Connection connection = DriverManager.getConnection(connectionString, connectionUsername, connectionPassword);
        Statement statement = connection.createStatement()) {
            statement.execute(usersTable.sqlScript);
            statement.execute(advertsTable.sqlScript);
            System.out.println("Migration successful.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Migration failed: " + e.getMessage());
        }
    }
}

