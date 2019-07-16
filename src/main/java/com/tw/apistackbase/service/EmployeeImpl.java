package com.tw.apistackbase.service;

import com.tw.apistackbase.model.Employee;

import java.util.List;

public interface EmployeeImpl {
    List<Employee> getEmployees();

    Employee findById(String employeeID);

    List<Employee> paging(int page, int pageSize);

    List<Employee> filterByGender(String gender);

    void addEmployee(Employee employee);

    void updateEmployee(String employeeID, Employee employee);

    void deleteEmployee(String employeeID);

}
