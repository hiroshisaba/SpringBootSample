package com.example.SpringBootSample.repository;

import com.example.SpringBootSample.domain.user.model.MUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insertOne(MUser user);

    List<MUser> findMany();
}
