package com.tw.apistackbase.service;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService implements CompanyImpl{

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company findByID(String companyID) {
        return companyRepository.findById(companyID);
    }

    @Override
    public List<Employee> getEmployees(String companyID) {
        return companyRepository.employees(companyID);
    }

    @Override
    public List<Company> paging(int page, int pageSize) {
        return companyRepository.paging(page,pageSize);
    }

    @Override
    public void addCompany(Company company) {
        companyRepository.addCompany(company);

    }

    @Override
    public void update(String companyID, Company company) {
        companyRepository.updateEmployee(companyID,company);

    }

    @Override
    public void delete(String companyID) {
        companyRepository.deleteEmployee(companyID);

    }

    public List<Company> getCompanies() {
        return companyRepository.companiesList();
    }
}
