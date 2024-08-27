package codeVibe.task.seeders;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Component
public class AdvertsSeeder {

    String sqlScript = """
            INSERT INTO adverts (title, fuel_type, price, is_new, mileage, first_registration)
            VALUES 
            ('Car A', 'Petrol', 15000, TRUE, 5000, '2022-01-15'),
            ('Car B', 'Diesel', 18000, FALSE, 12000, '2020-03-10'),
            ('Car C', 'Electric', 25000, TRUE, 2000, '2023-06-20'),
            ('Car D', 'Hybrid', 22000, FALSE, 8000, '2021-07-05'),
            ('Car E', 'Petrol', 14000, TRUE, 4000, '2022-12-10'),
            ('Car F', 'Diesel', 16000, FALSE, 15000, '2019-08-22'),
            ('Car G', 'Electric', 30000, TRUE, 1000, '2023-02-18'),
            ('Car H', 'Hybrid', 19000, FALSE, 9500, '2021-11-30'),
            ('Car I', 'Petrol', 13000, TRUE, 7000, '2022-04-25'),
            ('Car J', 'Diesel', 17000, FALSE, 13000, '2020-10-10');
        """;

    String tableName = "adverts";    

    public String getSqlScript() {
        return sqlScript;
    } 
      
}
