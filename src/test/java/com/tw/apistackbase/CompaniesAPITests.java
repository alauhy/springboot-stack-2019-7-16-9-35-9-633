package com.tw.apistackbase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.controller.CompanyController;
import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
public class CompaniesAPITests {
    @MockBean
    private CompanyService companyService;
    @Autowired
    private MockMvc mvc;


    @Test
    public void should_find_company_by_id() throws Exception {

        Company company = createCompany();
        when(companyService.findByID(anyString())).thenReturn(company);
        ResultActions resultActions = mvc.perform(get("/companies/{companyID}", company.getCompanyID()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", is("oocl")))
                .andExpect(jsonPath("$.employNumber", is(8)));
    }

    @Test
    public void should_add_company() throws  Exception{
        ResultActions resultActions = mvc.perform(post("/companies/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());

    }

    @Test
    public void should_update_company() throws  Exception{
        Company company = createCompany();
        ResultActions resultActions = mvc.perform(put("/companies/{companiesID}","1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(company)))
                .andExpect(status().isOk());

    }

    @Test
    public void should_delete_company() throws  Exception{
        Company company = createCompany();
        ResultActions resultActions = mvc.perform(delete("/companies/{companiesID}","1"))
                .andExpect(status().isOk());

    }

    private Company createCompany() {
        Company company = new Company();
        company.setCompanyName("oocl");
        company.setCompanyID("1");
        List<Employee> employees = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {

            Employee employee = new Employee();
            employee.setName("oop");
            employee.setAge(20);
            employee.setId(i + "");
            employee.setGender("female");
            employee.setSalary(7000);

            employees.add(employee);
        }
        company.setEmployees(employees);
        company.setEmployNumber(company.getEmployees().size());
        return company;
    }


}
