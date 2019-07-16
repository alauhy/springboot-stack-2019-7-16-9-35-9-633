package com.tw.apistackbase.service;

import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements EmployeeImpl {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.getEmployees();
    }

    @Override
    public Employee findById(String employeeID) {
        return employeeRepository.findById(employeeID);
    }

    @Override
    public List<Employee> paging(int page, int pageSize) {
        return employeeRepository.paging(page,pageSize);
    }

    @Override
    public List<Employee> filterByGender(String gender) {
        return employeeRepository.filterByGender(gender);
    }

    @Override
    public void addEmployee(Employee employee) {

        employeeRepository.addEmployee(employee);

    }

    @Override
    public void updateEmployee(String employeeID, Employee employee) {
        employeeRepository.updateEmployee(employeeID,employee);

    }

    @Override
    public void deleteEmployee(String employeeID) {
        employeeRepository.deleteEmployee(employeeID);

    }
}
