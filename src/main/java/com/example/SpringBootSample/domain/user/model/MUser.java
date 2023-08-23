package com.example.SpringBootSample.domain.user.model;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.util.Date;

@Data
public class MUser {
    private String userId;
    private String password;
    private String userName;
    private Date birthday;
    private Integer age;
    private Integer gender;
    private Integer departmentId;
    private String role;
}
