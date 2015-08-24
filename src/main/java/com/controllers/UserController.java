package com.controllers;

import com.Repository.User;
import com.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/users.json", method = RequestMethod.GET)
    public @ResponseBody List<User> getAllContacts(){
        return userRepository.getUsers();
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody String addNewUser (@RequestParam("Name") String name,
                                                @RequestParam("Login") String login,
                                                @RequestParam("Password") String password,
                                                @RequestParam("Email") String email) {

        User user = null;

        User.UserBuilder userBuilder = new User.UserBuilder(login, password).name(name).email(email);
        user = userBuilder.build();
        userRepository.addUser(user);
        return "";
    }

}
