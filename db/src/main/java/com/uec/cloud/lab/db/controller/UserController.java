package com.uec.cloud.lab.db.controller;

import com.uec.cloud.lab.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/list")
    @ResponseBody
    public Map<String, Object> list() {
        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", true);
        map.put("result", userService.list());

        return map;
    }

    @GetMapping(path = "/find/{name}")
    public Map<String, Object> find(@PathVariable("name") String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", true);
        map.put("result", userService.find(name));

        return map;
    }
}
