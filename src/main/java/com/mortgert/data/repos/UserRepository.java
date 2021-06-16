package com.mortgert.data.repos;

import com.mortgert.data.models.Role;
import com.mortgert.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String username);
    Boolean existsUserByUsernameAndPassword(String username,String password);
}
