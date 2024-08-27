package codeVibe.task.model;

import codeVibe.task.model.Users;
import codeVibe.task.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<Users>> getAllUsers(@RequestParam(value = "sortBy", required = false) String sortBy) {
      List<Users> users = userService.getAllUsers(sortBy);
      return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Users> getUserById(@PathVariable Long id) {
      Users user = userService.getUserById(id);
      return ResponseEntity.ok(user);
  }

  @PostMapping
  public ResponseEntity<Users> createUser(@RequestBody Users user) {
      Users createdUser = userService.createUser(user);
      return ResponseEntity.status(201).body(createdUser);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users user) {
      Users updatedUser = userService.updateUser(id, user);
      return ResponseEntity.ok(updatedUser);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
      userService.deleteUser(id);
      return ResponseEntity.noContent().build();
  }
}
