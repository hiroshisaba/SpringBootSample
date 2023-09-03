package com.example.SpringBootSample.domain.user.service;

import com.example.SpringBootSample.domain.user.model.MUser;

import java.util.List;

public interface UserService {

    void signup (MUser user);

    List<MUser> getUsers();

    MUser getUserOne(String userId);
}
