package com.masai.service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.masai.expection.CandleClassException;
import com.masai.expection.InputNotFound;
import com.masai.model.Candle;
import com.masai.repo.CandleRepo;
@Service
public class CandleServiceImpl implements CandleInterface{
	@Autowired
	private CandleRepo candleRepository;

	@Override
	public String addData() throws CandleClassException {
		 try (InputStream inputStream = new ClassPathResource("candles.json").getInputStream()) {
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.registerModule(new JavaTimeModule());

	            JsonNode rootNode = objectMapper.readTree(inputStream);
	            JsonNode candlesNode = rootNode.get("candles");

	            if (candlesNode != null && candlesNode.isArray()) {
	                List<Candle> candles = new ArrayList<>();
	                for (JsonNode candleNode : candlesNode) {
	                    Candle candle = objectMapper.convertValue(candleNode, Candle.class);
	                    candles.add(candle);
	                }
	                Collections.sort(candles, Comparator.comparing(Candle::getLastTradeTime));
	                candleRepository.saveAll(candles);
	            }

	            return "Data added Succesfully";
	        } catch (IOException e) {
	            throw new CandleClassException("Something went Wrong");
	        }
	    
	}


	@Override
	public List<Candle> getCandlesData() throws InputNotFound {
		return candleRepository.getFirstAndLastCandlesOfTheDay();
	}

	@Override
	public String getFirstOrbCandleData(Integer time) throws CandleClassException, InputNotFound {
	    if (time == null) {
	        throw new InputNotFound("Time interval is required.");
	    }
	    LocalDateTime startDate = LocalDateTime.now().with(LocalTime.of(9, 15));
	    LocalDateTime endDate = startDate.plusMinutes(time);

	    List<Candle> candles = candleRepository.findByLastTradeTimeBetween(startDate, endDate);

	    if (candles.isEmpty()) {
	        throw new CandleClassException("No candles found in the specified interval");
	    }

	    double openingRangeHigh = candles.stream().mapToDouble(Candle::getHigh).max().orElse(0.0);
	    double openingRangeLow = candles.stream().mapToDouble(Candle::getLow).min().orElse(0.0);

	    List<Candle> subsequentCandles = candleRepository.findByLastTradeTimeAfter(endDate);

	    for (Candle candle : subsequentCandles) {
	        if (candle.getClose() > openingRangeHigh || candle.getClose() < openingRangeLow) {
	            return "First Opening Range Breakout Candle found at: " + candle.getLastTradeTime();
	        }
	    }

	    throw new CandleClassException("No opening range breakout occurred.");
	}


	@Override
	public List<Candle> getCandlesNewInterval(Integer time) throws CandleClassException, InputNotFound {
	    if (time == null) {
	        throw new InputNotFound("Time interval is required.");
	    }

	    LocalDateTime currentDateTime = LocalDateTime.now();
	    LocalDateTime newIntervalStart = currentDateTime.minusMinutes(time);
	    List<Candle> candles = candleRepository.findByLastTradeTimeBetween(newIntervalStart, currentDateTime);

	    if (candles.isEmpty()) {
	        throw new CandleClassException("No candles found in the specified interval");
	    }

	    return candles;
	}


	

}
