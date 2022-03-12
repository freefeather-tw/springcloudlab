package com.uec.cloud.lab.apigateway.controller;

import com.uec.cloud.lab.apigateway.facade.EchoClientFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/echo")
public class EchoController {
    @Autowired
    EchoClientFacade echoClientFacade;

    @GetMapping(path = "/{message}")
    public final Map<String, Object> echo(
            @PathVariable("message") final String message) {

        return echoClientFacade.echo(message);

    }
}
