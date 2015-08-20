package com.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by rostyslav on 20.08.15.
 */
public class IndexController {

    @RequestMapping("/")
    public String getIndex() {
        return "index";
    }
}
