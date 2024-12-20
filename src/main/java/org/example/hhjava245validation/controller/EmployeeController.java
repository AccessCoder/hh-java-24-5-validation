package org.example.hhjava245validation.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.example.hhjava245validation.model.Employee;
import org.example.hhjava245validation.service.EmployeeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Validated //-> Aktiviert ValidierungsAnnotations im Controller. NICHT benötigt für @Valid
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/search")
    public Employee getEmployeeByPhoneNumber(@Pattern(regexp = "^\\d{10}$", message = "Telefonnummer muss aus 10 Ziffern bestehen")
                                                 @RequestParam String phone) {
        return service.getEmployeeByPhoneNumber(phone);
    }

    @PostMapping
    //@Valid = "Aktiviert" ValidierungsAnnotations im Model. @Valid braucht @Validated NICHT
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return service.createEmployee(employee);
    }
}
