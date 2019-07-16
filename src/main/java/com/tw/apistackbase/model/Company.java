package com.tw.apistackbase.model;

import java.util.List;

public class Company {
    private String companyName;
    private String companyID;
    private int employNumber;
    private List<Employee> employees;

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEmployNumber() {
        return employNumber;
    }

    public void setEmployNumber(int employNumber) {
        this.employNumber = employNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
