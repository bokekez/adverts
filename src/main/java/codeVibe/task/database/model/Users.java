package codeVibe.task.model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Users {

  private Long id;
  private String username;
  private String password;
  private String email;
  private Date createdAt;
  private Date updatedAt;

  public Users(long id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
    }

    public Users() {
    }

  public Long getId() {
      return id;
  }

  public void setId(Long id) {
      this.id = id;
  }

  public String getUsername() {
      return username;
}

  public void setUsername(String username) {
      this.username = username;
  }

  public String getPassword() {
      return password;
  }

  public void setPassword(String password) {
      this.password = password;
  }

  public String getEmail() {
      return email;
  }

  public void setEmail(String email) {
      this.email = email;
  }

  public Date getCreatedAt() {
      return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
      return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
      this.updatedAt = updatedAt;
  }

  String sqlScript = """
    CREATE TABLE users (
        id SERIAL PRIMARY KEY,
        username VARCHAR(255) NOT NULL,
        password VARCHAR(255) NOT NULL,
        email VARCHAR(255) NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
    );

    CREATE OR REPLACE FUNCTION update_updated_at_column()
    RETURNS TRIGGER AS $$
    BEGIN
        NEW.updated_at = CURRENT_TIMESTAMP;
        RETURN NEW;
    END;
    $$ LANGUAGE plpgsql;

    CREATE TRIGGER update_users_updated_at
    BEFORE UPDATE ON users
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();
    """;    

}
