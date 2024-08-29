package codeVibe.task.service;

import codeVibe.task.repository.UserDAO;
import codeVibe.task.model.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<Users> getAllUsers(String sortBy) {
        return userDAO.getAllUsers(sortBy);
    }

    public Users getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    public Users createUser(Users user) {
        return userDAO.createUser(user);
    }

    public Users updateUser(Long id, Users user) {
        return userDAO.updateUser(id, user);
    }

    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }
}
