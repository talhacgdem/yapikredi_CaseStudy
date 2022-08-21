package com.talhacgdem.yapikredi.repository;

import com.talhacgdem.yapikredi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
