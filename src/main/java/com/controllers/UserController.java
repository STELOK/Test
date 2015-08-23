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

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody String addNewUser (@RequestParam("name") String name,
                                                @RequestParam("login") String login,
                                                @RequestParam("password_hash") String password,
                                                @RequestParam("email") String email) {

        User user = null;
        user = new User.UserBuilder(login, password).name(name).email(email).build();
        userRepository.addUser(user);
        return "";
    }

}
