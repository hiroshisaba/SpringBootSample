package com.example.SpringBootSample.domain.user.service.impl;

import com.example.SpringBootSample.domain.user.model.MUser;
import com.example.SpringBootSample.domain.user.service.UserService;
import com.example.SpringBootSample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserServiceImpl2 implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;


    @Transactional
    @Override
    public void signup(MUser user) {
        boolean isExists = repository.existsById(user.getUserId());
        if(isExists) {
            throw new DataAccessException("ユーザーが既に存在"){};
        }
        user.setDepartmentId(1);
        user.setRole("ROLE_GENERAL");

        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        repository.save(user);
    }

    @Override
    public List<MUser> getUsers(MUser user) {
        // 検索条件
        ExampleMatcher matcher = ExampleMatcher.matching()//and条件
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)// Like区
                .withIgnoreCase();//大文字・小文字の両方
        return repository.findAll(Example.of(user,matcher));
    }

    @Override
    public MUser getUserOne(String userId) {
        Optional<MUser> option = repository.findById(userId);
        MUser user = option.orElse(null);
        return user;
    }

    @Transactional
    @Override
    public void updateUserOne(String userId, String password, String userName) {

        String encryptPassword = encoder.encode(password);
        repository.updateUser(userId,password,userName);
    }

    @Transactional
    @Override
    public void deleteUserOne(String userId) {
        repository.deleteById(userId);
    }

    @Override
    public MUser getLoginUser(String userId) {
        return repository.findLoginUser(userId);
    }
}
