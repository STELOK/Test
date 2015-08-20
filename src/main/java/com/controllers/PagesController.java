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
        return "forward:/resources/templates/index.html";
    }

    @RequestMapping("/contacts")
    public String getContactsPage() {
        return "forward:/resources/templates/contacts.html";
    }
}
