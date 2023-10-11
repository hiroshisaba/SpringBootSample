package com.example.SpringBootSample.domain.user.service;

import com.example.SpringBootSample.domain.user.model.MUser;

import java.util.List;

public interface UserService {

    void signup (MUser user);

    List<MUser> getUsers(MUser user);

    MUser getUserOne(String userId);

    void updateUserOne(String userId, String password, String userName);

    void deleteUserOne(String userId);

    MUser getLoginUser(String userId);
}
