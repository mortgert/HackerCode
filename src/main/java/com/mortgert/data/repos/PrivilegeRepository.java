package com.mortgert.data.repos;

import com.mortgert.data.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege,String> {
    Privilege findByName(String name);
}
