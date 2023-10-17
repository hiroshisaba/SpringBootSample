package com.example.SpringBootSample.rest;

import com.example.SpringBootSample.domain.user.model.MUser;
import com.example.SpringBootSample.domain.user.service.UserService;
import com.example.SpringBootSample.form.GroupOrder;
import com.example.SpringBootSample.form.SignupForm;
import com.example.SpringBootSample.form.UserDetailForm;
import com.example.SpringBootSample.form.UserListForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    /**
     * ユーザーを検索
     */
    @GetMapping("/get/list")
    public List<MUser> getUserList(UserListForm form) {

        MUser user = modelMapper.map(form, MUser.class);
        List<MUser> userList = userService.getUsers(user);
        return userList;
    }
    /**
     * ユーザーを登録
     */
    @PostMapping("/signup/rest")
    public RestResult postSignup(@Validated(GroupOrder.class)SignupForm form, BindingResult bindingResult, Locale locale) {
        if(bindingResult.hasErrors()) {
            // チェック結果:NG
            Map<String, String> errors = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()){
                String message = messageSource.getMessage(error, locale);
                errors.put(error.getField(), message);
            }
            return new RestResult(90, errors);
        }
        // formをMUserクラスに変換
        MUser user = modelMapper.map(form, MUser.class);
        userService.signup(user);
        return new RestResult(0, null);
    }
    /**
     * ユーザーを更新
     */
    @PutMapping("/update")
    public int updateUser(UserDetailForm form) {
        // ユーザーを更新
        userService.updateUserOne(form.getUserId(), form.getPassword(), form.getUserName());
        return 0;
    }

    @DeleteMapping("/delete")
    public int deleteUser(UserDetailForm form) {
        //ユーザーを削除
        userService.deleteUserOne(form.getUserId());
        return 0;
    }
}
