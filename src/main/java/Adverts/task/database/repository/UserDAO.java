package Adverts.task.database.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;

import Adverts.task.model.Users;

public class UserDAO {

    @Value("${spring.datasource.url}")
    private String connectionString;

    @Value("${spring.datasource.username}")
    private String connectionUsername;

    @Value("${spring.datasource.password}")
    private String connectionPassword;

    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Users> getAllUsers(String sortBy) {
        List<Users> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        if (sortBy != null && !sortBy.isEmpty()) {
            query += " ORDER BY " + sortBy;
        }

        try (PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                user.setUpdatedAt(rs.getTimestamp("updated_at"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public Users getUserById(Long id) {
        Users user = null;
        String query = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new Users();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setCreatedAt(rs.getTimestamp("created_at"));
                    user.setUpdatedAt(rs.getTimestamp("updated_at"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public Users createUser(Users user) {
        String query = "INSERT INTO users (username, password, email, created_at, updated_at) VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP) RETURNING id";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user.setId(rs.getLong(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public Users updateUser(Long id, Users user) {
        String query = "UPDATE users SET username = ?, password = ?, email = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setLong(4, id);

            stmt.executeUpdate();
            user.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public void deleteUser(Long id) {
        String query = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<Users> findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Users user = new Users(
                            rs.getLong("id"),
                            rs.getString("username"),
                            rs.getString("password"));
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
