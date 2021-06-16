package com.mortgert.data.repos;

import com.mortgert.data.models.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem,String> {
    Problem findByProblemID(String id);
}
