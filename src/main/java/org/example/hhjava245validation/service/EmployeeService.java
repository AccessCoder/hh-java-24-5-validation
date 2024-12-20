package org.example.hhjava245validation.service;

import org.example.hhjava245validation.model.Employee;
import org.example.hhjava245validation.repo.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepo repo;

    public EmployeeService(EmployeeRepo repo) {
        this.repo = repo;
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public Employee getEmployeeByPhoneNumber(String phoneNumber) {
        return repo.findByPhone(phoneNumber)
                .orElseThrow();
    }

    public Employee createEmployee(Employee employee) {
        return repo.save(employee);
    }
}
