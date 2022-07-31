package com.example.revenueservice.controllers;

import com.example.revenueservice.entities.Counter;
import com.example.revenueservice.services.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/revenue/counters")
public class CounterController {

    @Autowired
    CounterService counterService;

    @GetMapping("/{name}")
    public ResponseEntity<Counter> getCountersByName(@PathVariable("name") String name) {
        Counter counter = counterService.getCounterByName(name);
        return new ResponseEntity<>(counter, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Counter> createCounter(@RequestBody() Counter counter) {
        Counter createdCounter = counterService.createCounter(counter);
        return new ResponseEntity<>(createdCounter, HttpStatus.OK);
    }

}
