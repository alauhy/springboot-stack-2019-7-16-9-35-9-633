package com.tw.apistackbase.repository;


import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {

    private static Map<String, Company> companies = new HashMap<>();
    static {
        companies.put("1", createCompany("alibaba"));
        companies.put("2", createCompany("tecent"));

    }

    public static Company createCompany(String name) {
        Company company = new Company();
        company.setCompanyName(name);
        company.setCompanyID(UUID.randomUUID().toString());
        List<Employee> employees = new ArrayList<>();
        for(int i = 1; i<=8 ; i++){

            Employee employee = new Employee();
            employee.setName("akb");
            employee.setAge(20);
            employee.setId(i+"");
            employee.setGender("female");
            employee.setSalary(7000);

            employees.add(employee);
        }
        company.setEmployees(employees);
        company.setEmployNumber(company.getEmployees().size());
        return company;
    }

    public List<Company> companiesList() {
        return new ArrayList<>(companies.values());
    }

    public Company findById(String companyID) {
        return companies.get(companyID);
    }

    public List<Employee> employees(String companyID){
        return companies.get(companyID).getEmployees();
    }

    public void addCompany(Company company) {
        company.setCompanyID(UUID.randomUUID().toString());
        companies.put(company.getCompanyID(), company);
    }

    public void updateEmployee(String companyID, Company company) {
        companies.remove(companyID);
        companies.put(companyID,company);
    }

    public void deleteEmployee(String employeeID) {
        companies.remove(employeeID);
    }

    public List<Company> paging(int page, int pageSize) {
        return companies.values().stream().skip(Math.max(0,page-1)*pageSize).limit(pageSize).collect(Collectors.toList());



    }
}
