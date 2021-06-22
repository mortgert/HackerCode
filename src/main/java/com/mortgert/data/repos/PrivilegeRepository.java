package com.mortgert.data.repos;

import com.mortgert.data.models.Privilege;
import com.mortgert.data.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PrivilegeRepository extends JpaRepository<Privilege,String> {
    Privilege findByName(String name);
    List<Privilege> findAllByRoles(Role role);
}
