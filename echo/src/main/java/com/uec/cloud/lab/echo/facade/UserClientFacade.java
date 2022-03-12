package com.uec.cloud.lab.echo.facade;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "APIGATEWAY/db/User")
public interface UserClientFacade {

    @GetMapping(path = "/list")
    Map<String, Object> list();

    @GetMapping(path = "/find/{name}")
    Map<String, Object> find(@PathVariable("name") String name);

}
