package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> companiesList() {
        return companyService.getCompanies();
    }

    @GetMapping("/companies/{companyID}")
    public Company targetCompany(@PathVariable String companyID) {
        return companyService.findByID(companyID);
    }

    @GetMapping("/companies/{companyID}/employees")
    public List<Employee> employeesList(@PathVariable String companyID) {
        return companyService.getEmployees(companyID);
    }

    @GetMapping(path="/companies",params = "page,pageSize")
    public List<Company> showCompanies(@RequestParam int page,@RequestParam int pageSize){
        return companyService.paging(page,pageSize);
    }

    @PostMapping("/companies")
    public void addCompany(@RequestBody Company company){
        companyService.addCompany(company);
    }

    @PutMapping("/companies/{companiesID}")
    public void updateCompany(@PathVariable String companyID,@RequestBody Company company){
        companyService.update(companyID,company);
    }
    @DeleteMapping("/companies/{companiesID}")

    public void deleteCompany(@PathVariable String companyID,@RequestBody Company company){
        companyService.delete(companyID,company);
    }

}
