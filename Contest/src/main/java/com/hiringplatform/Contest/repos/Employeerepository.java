package com.hiringplatform.Contest.repos;

import com.hiringplatform.Contest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Employeerepository extends JpaRepository<Employee,Integer> {
    Employee findByEmail(String email);

    boolean existsByEmail(String email);
}
