package com.mortgert.data.repos;

import com.mortgert.data.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,String> {
    Role findByName(String name);
    List<Role> findByUsers_Username(String username);

}
