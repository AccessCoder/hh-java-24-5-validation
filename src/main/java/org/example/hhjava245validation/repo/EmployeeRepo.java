package org.example.hhjava245validation.repo;

import org.example.hhjava245validation.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, String> {

    Optional<Employee> findByPhone(String phone);
}
