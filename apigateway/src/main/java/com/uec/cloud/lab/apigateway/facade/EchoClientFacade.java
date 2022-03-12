package com.uec.cloud.lab.apigateway.facade;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@FeignClient(name = "echoClientFacade", url = "http://localhost:8181/echo")
public interface EchoClientFacade {

    @GetMapping(path = "/{message}")
    @ResponseBody
    Map<String, Object> echo(@PathVariable("message") String message);
}
