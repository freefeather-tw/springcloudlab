package com.uec.cloud.lab.echo.controller;

import com.uec.cloud.lab.echo.facade.UserClientFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserClientFacade userClientFacade;

    @GetMapping(path = "/list")
    public final Map<String, Object> list() {
        return userClientFacade.list();
    }

    @GetMapping(path = "/find/{name}")
    public final Map<String, Object> find(@PathVariable("name") String name) {
        return userClientFacade.find(name);
    }
}
