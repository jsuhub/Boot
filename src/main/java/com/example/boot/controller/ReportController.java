package com.example.boot.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/reports")
public class ReportController {


    @RequestMapping(value = {"name", "name2"} )
    String ete() {
        return "shhao";
    }
}
