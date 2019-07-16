package com.tw.apistackbase.service;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CompanyImpl {
    List<Company> getCompanies();

    Company findByID(String companyID);

    List<Employee> getEmployees(String companyID);

    List<Company> paging(int page, int pageSize);

    void addCompany(Company company);

    void update(String companyID, Company company);

    void delete(String companyID, Company company);

}
