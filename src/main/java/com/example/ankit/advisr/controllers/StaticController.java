package com.example.ankit.advisr.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping({"app", "/", ""})
public class StaticController {

    private static final String FILE_NAME = "index";

    public String index() {
        return FILE_NAME;
    }

}
