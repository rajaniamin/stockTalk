package com.masai.service;

import java.io.InputStream;
import java.time.LocalDateTime;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirstOrbCandle(Integer time) throws CandleClassException, InputNotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Candle> getCandlesWithNewInterval(Integer time) throws CandleClassException, InputNotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalDateTime findORBDateTime(int intervalMinutes) throws CandleClassException {
		// TODO Auto-generated method stub
		return null;
	}

}
