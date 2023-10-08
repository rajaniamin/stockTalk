package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.expection.CandleClassException;
import com.masai.expection.InputNotFound;
import com.masai.model.Candle;
import com.masai.service.CandleInterface;

import java.util.List;



@RestController
@RequestMapping("/candles")
public class CandleController {
    @Autowired
    private CandleInterface candleService;

 // Endpoint to add candle data to the database.
    @GetMapping("/add")
    public ResponseEntity<String> addCandleData() {
        try {
            String result = candleService.addData();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (CandleClassException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    // Endpoint to get the first Opening Range Breakout (ORB) candle.

    @GetMapping("/get-first-orb-candle")
    public ResponseEntity<String> getFirstOrbCandle(Integer time) {
        try {
            String result = candleService.getFirstOrbCandleData(time);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (CandleClassException | InputNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
 // Endpoint to get candles for a new time interval.

    @GetMapping("/get-candles-new-interval")
    public ResponseEntity<List<Candle>> getCandlesNewInterval(Integer time) {
        try {
            List<Candle> candles = candleService.getCandlesNewInterval(time);
            return new ResponseEntity<>(candles, HttpStatus.OK);
        } catch (CandleClassException | InputNotFound e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
