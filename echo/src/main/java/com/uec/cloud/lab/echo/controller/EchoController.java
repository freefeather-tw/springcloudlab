package com.uec.cloud.lab.echo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class EchoController {

    @GetMapping(path = "/{message}")
    @ResponseBody
    public final Map<String, Object> echo(
            @PathVariable("message") final String message) {

        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", true);
        map.put("result", "echo => " + message);

        return map;
    }
}
