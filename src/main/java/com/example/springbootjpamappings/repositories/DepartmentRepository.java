package com.example.springbootjpamappings.repositories;

import com.example.springbootjpamappings.entities.DepartmentEntity;
import com.example.springbootjpamappings.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {

    DepartmentEntity findByManager(EmployeeEntity employeeEntity);
}
