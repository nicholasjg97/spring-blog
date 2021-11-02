package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{int1}/and/{int2}")
    @ResponseBody
    public int add(@PathVariable int int1, @PathVariable int int2) {
        return int1 + int2;
    }

    @GetMapping("/subtract/{int1}/and/{int2}")
    @ResponseBody
    public int subtract(@PathVariable int int1, @PathVariable int int2) {
        return int1 - int2;
    }

    @GetMapping("/multiply/{int1}/and/{int2}")
    @ResponseBody
    public int multiply(@PathVariable int int1, @PathVariable int int2) {
        return int1 * int2;
    }

    @GetMapping("/divide/{int1}/and/{int2}")
    @ResponseBody
    public int divide(@PathVariable int int1, @PathVariable int int2) {
        return int1 / int2;
    }



}
