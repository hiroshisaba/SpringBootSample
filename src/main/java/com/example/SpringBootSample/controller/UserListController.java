package com.example.SpringBootSample.controller;

import com.example.SpringBootSample.domain.user.model.MUser;
import com.example.SpringBootSample.domain.user.service.UserService;
import com.example.SpringBootSample.form.UserListForm;
//import com.sun.org.apache.xpath.internal.operations.Mod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserListController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public String getUserList(@ModelAttribute UserListForm form, Model model) {

        MUser user = modelMapper.map(form , MUser.class);
        List<MUser> userList = userService.getUsers(user);
        model.addAttribute("userList",userList);
        return "user/list";
    }

    @PostMapping("/list")
    public String postUserList(@ModelAttribute UserListForm form, Model model) {

        MUser user = modelMapper.map(form , MUser.class);
        List<MUser> userList = userService.getUsers(user);
        model.addAttribute("userList", userList);
        return "user/list";
    }
}
