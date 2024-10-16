package Adverts.task.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import Adverts.task.repository.AdvertsDAO;
import Adverts.task.repository.UserDAO;
import Adverts.task.service.AdvertsService;
import Adverts.task.service.UserService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class Services {

    private final DataSource dataSource;

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

}
