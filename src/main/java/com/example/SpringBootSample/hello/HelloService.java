package com.example.SpringBootSample.hello;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HelloService {
    @Autowired
    private HelloRepository helloRepository;

    public Employee getEmployee(String id) {
        Map<String, Object> map = helloRepository.findById(id);

        String employeeId = (String) map.get("id");
        String name = (String) map.get("name");
        int age = (Integer) map.get("age");

        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setEmployeeName(name);
        employee.setEmployeeAge(age);
        return employee;
    }

}
