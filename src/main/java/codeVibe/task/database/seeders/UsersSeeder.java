package codeVibe.task.seeders;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Component
public class UsersSeeder {

    String sqlScript = """
            INSERT INTO users (username, password, email)
            VALUES 
            ('Admin', 'Admin', 'Admin@admin.com'),
            ('Admin2', 'Admin2', 'Admin2@admin.com');
        """;

    String tableName = "users";

    public String getSqlScript() {
        return sqlScript;
    } 
      
}
