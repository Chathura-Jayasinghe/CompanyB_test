package com.example.CompanyB.TrainingSimulationPrototypingModule.Controller;

import com.example.CompanyB.TrainingSimulationPrototypingModule.Model.Employee;
import com.example.CompanyB.TrainingSimulationPrototypingModule.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/get/{employeeId}")
    public Employee getEmployeeById(@PathVariable String employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("/selectCourse/{employeeId}/{skillLevel}")
    public void selectCourseForEmployee(
            @PathVariable String employeeId,
            @PathVariable int skillLevel
    ) {
        employeeService.selectCourseForEmployee(employeeId, skillLevel);
    }

}