package com.example.springbootjpamappings.repositories;

import com.example.springbootjpamappings.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository  extends JpaRepository<EmployeeEntity,Long> {

}
