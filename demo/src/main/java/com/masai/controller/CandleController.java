package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.expection.CandleClassException;
import com.masai.service.CandleInterface;

@RestController
public class CandleController {
	
	@Autowired
    private CandleInterface candleService;
    
    @GetMapping("/add")
    public ResponseEntity<String> addCandleData() {
        try {
            String result = candleService.addData();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (CandleClassException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    

}
