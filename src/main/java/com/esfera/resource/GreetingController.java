package com.esfera.resource;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting(@RequestParam(name="name", required=false, defaultValue="World") String name) {

        return ResponseEntity.ok("cambio el saludo");
    }

}