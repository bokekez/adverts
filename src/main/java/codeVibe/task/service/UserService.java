package codeVibe.task.service;

import codeVibe.task.repository.UserDAO;
import codeVibe.task.model.Users;

import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // Fetch all users with optional sorting by any column
    public List<Users> getAllUsers(String sortBy) {
        return userDAO.getAllUsers(sortBy);
    }

    // Fetch a single user by ID
    public Users getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    // Create a new user
    public Users createUser(Users user) {
        return userDAO.createUser(user);
    }

    // Update an existing user
    public Users updateUser(Long id, Users user) {
        return userDAO.updateUser(id, user);
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }
}
