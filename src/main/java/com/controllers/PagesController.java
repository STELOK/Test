package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by rostyslav on 20.08.15.
 */
@Controller
public class PagesController {

    @RequestMapping("/")
    public String getIndex() {
        return "index";
    }

    @RequestMapping("/users")
    public String getContactsPage() {
        return "users";
    }

    @RequestMapping("/authorization")
    public String getAuthorizationPage() {
        return "Authorization";
    }

}
