package com.controllers;

import com.Repository.User;
import com.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/users.json", method = RequestMethod.GET)
    public @ResponseBody List<User> getAllContacts(){
        return userRepository.getUsers();
    }

    @RequestMapping("/createUser")
    public void createUser() {
        userRepository.addUser();
    }


}
