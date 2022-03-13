package com.uec.cloud.lab.echo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
@Slf4j
public class EchoController {

    @GetMapping(path = "/{message}")
    @ResponseBody
    public final Map<String, Object> echo(
            @PathVariable("message") final String message) {

        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", true);
        map.put("result", "echo => " + message);
        log.debug("call echo() ... ");
        return map;
    }
}
