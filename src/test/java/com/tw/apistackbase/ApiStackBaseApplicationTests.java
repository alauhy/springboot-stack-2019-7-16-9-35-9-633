package com.tw.apistackbase;

import com.tw.apistackbase.controller.CompanyController;

import com.tw.apistackbase.controller.EmployeeController;
import com.tw.apistackbase.model.Employee;

import com.tw.apistackbase.service.EmployeeService;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class ApiStackBaseApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;

    @Test
    public void should_find_employee_by_id() throws Exception {

        Employee employee = new Employee();
        employee.setId("1");
        employee.setGender("female");
        employee.setName("lxy");
        employee.setSalary(6000);
        employee.setAge(20);

        when(employeeService.findById(anyString())).thenReturn(employee);

        ResultActions resultActions = mockMvc.perform(get("/employees/{employeeID}", employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("lxy")))
                .andExpect(jsonPath("$.gender", is("female")))
                .andExpect(jsonPath("$.salary", is(6000)));


    }

    @Test
    public void should_find_employee_filter_gender() throws Exception {

        Employee employee = new Employee();
        employee.setId("1");
        employee.setGender("female");
        employee.setName("lxy");
        employee.setSalary(6000);
        employee.setAge(20);
        List<Employee> list = new ArrayList<>();
        list.add(employee);

        when(employeeService.filterByGender(anyString())).thenReturn(list);

        ResultActions resultActions = mockMvc.perform(get("/employees?gender=female"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("lxy")))
                .andExpect(jsonPath("$[0].gender", is("female")))
                .andExpect(jsonPath("$[0].salary", is(6000)));


    }

    @Test
    public void should_show_employee_pagesize_per_page() throws Exception {

        Employee employee = new Employee();
        employee.setId("1");
        employee.setGender("female");
        employee.setName("lxy");
        employee.setSalary(6000);
        employee.setAge(20);
        List<Employee> list = new ArrayList<>();
        list.add(employee);


        when(employeeService.paging(anyInt(), anyInt())).thenReturn(list);

        ResultActions resultActions = mockMvc.perform(get("/employees?page=1&pageSize=1"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("lxy")))
                .andExpect(jsonPath("$[0].gender", is("female")))
                .andExpect(jsonPath("$[0].salary", is(6000)));


    }

    @Test
    public void should_put_employee() throws Exception {

        Employee employee = new Employee();
        employee.setId("1");
        employee.setGender("female");
        employee.setName("lxy");
        employee.setSalary(6000);
        employee.setAge(20);


        ResultActions resultActions = mockMvc.
                perform(put("/employees/{employeeID}", employee.getId())
                        .contentType(MediaType.APPLICATION_JSON).
                                content("{\n" +
                                        "\t\"name\" : \"lxy\",\n" +
                                        "\t\"age\" :22,\n" +
                                        "\t\"gendar\" :\"male\"\n" +
                                        "}"))
                .andExpect(status().isOk());


    }

    @Test
    public void should_delete_employee() throws Exception {


        ResultActions resultActions = mockMvc.
                perform(delete("/employees/{employeeID}", "1"))
                .andExpect(status().isOk());


    }



}
