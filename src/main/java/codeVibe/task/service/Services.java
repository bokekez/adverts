package codeVibe.task.service;

import codeVibe.task.repository.AdvertsDAO;
import codeVibe.task.repository.UserDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class Services {

    private final DataSource dataSource;

    // Inject the DataSource bean in the constructor
    public Services(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public AdvertsDAO advertsDAO() {
        try {
            Connection connection = dataSource.getConnection();
            return new AdvertsDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create AdvertsDAO", e);
        }
    }

    @Bean
    public AdvertsService advertsService(AdvertsDAO advertsDAO) {
        return new AdvertsService(advertsDAO);
    }

    @Bean
    public UserDAO userDAO() {
        try {
            Connection connection = dataSource.getConnection();
            return new UserDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create UserDAO", e);
        }
    }

    @Bean
    public UserService userService(UserDAO userDAO) {
        return new UserService(userDAO);
    }

    public void performServiceTasks() {
        AdvertsService advertsService = advertsService(advertsDAO());
        UserService userService = userService(userDAO());
        System.out.println("Running services");
    }
}
