package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> employeesList() {
        return employeeService.getEmployees();
    }

    @GetMapping("/employees/{employeeID}")
    public Employee targetEmployee(@PathVariable String employeeID) {
        return employeeService.findById(employeeID);
    }

    @GetMapping(path = "/employees", params = "page,pageSize")
    public List<Employee> paging(@RequestParam int page, @RequestParam int pageSize) {
        return employeeService.paging(page, pageSize);
    }

    @GetMapping(path = "/employees",params = "gender")
    public List<Employee> findByGender(@RequestParam String gender){
        return employeeService.filterByGender(gender);
    }

    @PostMapping("/employees")
    public void addEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
    }
    @PutMapping("/employees/{employeeID}")
    public void updateEmployee(@PathVariable String employeeID,@RequestBody Employee employee){
        employeeService.updateEmployee(employeeID,employee);
    }
    @DeleteMapping("/employees/{employeeID}")
    public void deleteEmployee(@PathVariable String employeeID){
        employeeService.deleteEmployee(employeeID);
    }



}
