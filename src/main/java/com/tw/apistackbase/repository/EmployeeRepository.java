package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private static Map<String, Employee> employees = new HashMap<>();

    static {
        employees.put("1", createEmployee("lxy", "male", 20, 4000));
        employees.put("2", createEmployee("yyr", "male", 20, 5000));
        employees.put("3", createEmployee("dtt", "female", 21, 6000));
        employees.put("4", createEmployee("jfy", "female", 22, 7000));
        employees.put("5", createEmployee("jfk", "female", 22, 8000));
        employees.put("6", createEmployee("jfl", "female", 22, 9000));
        employees.put("7", createEmployee("jfi", "female", 22, 10000));
        employees.put("8", createEmployee("jfo", "female", 22, 11000));


    }

    public static Employee createEmployee(String name, String gender, int age, int salary) {
        Employee employee = new Employee();
        employee.setAge(age);
        employee.setId(UUID.randomUUID().toString());
        employee.setSalary(salary);
        employee.setName(name);
        employee.setGender(gender);
        return employee;
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees.values());
    }

    public void addEmployee(Employee employee) {
        employee.setId(UUID.randomUUID().toString());
        employees.put(employee.getId(), employee);
    }

    public void updateEmployee(String employeeID, Employee employee) {
        employees.remove(employeeID);
        employees.put(employeeID, employee);
    }

    public void deleteEmployee(String employeeID) {
        employees.remove(employeeID);
    }

    public List<Employee> paging(int page, int pageSize) {

        return employees.values().stream().skip(Math.max(0,page-1)*pageSize).limit(pageSize).collect(Collectors.toList());


    }
    public Employee findById(String employeeID){
        return employees.get(employeeID);
    }
    public List<Employee> filterByGender(String gender){
        return employees.values().stream().filter(e->e.getGender().equals(gender)).collect(Collectors.toList());
    }

}
