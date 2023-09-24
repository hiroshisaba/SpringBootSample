package com.example.SpringBootSample.repository;

import com.example.SpringBootSample.domain.user.model.MUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int insertOne(MUser user);

    List<MUser> findMany(MUser user);

    MUser findOne(String userId);

    void updateOne(@Param("userId") String userId, @Param("password") String password, @Param("userName") String userName);

    int deleteOne(@Param("userId") String userId);
}
