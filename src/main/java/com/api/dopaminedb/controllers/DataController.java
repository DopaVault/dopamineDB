package com.api.dopaminedb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/dopa")
public class DataController {

    @GetMapping("data")
    public String getData() {
        return "Data from DopamineDB";
    }
}
