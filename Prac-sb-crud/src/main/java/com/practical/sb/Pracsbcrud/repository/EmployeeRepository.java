package com.practical.sb.Pracsbcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.practical.sb.Pracsbcrud.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
