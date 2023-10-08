package com.masai.service;

import java.util.List;

import com.masai.expection.CandleClassException;
import com.masai.expection.IOException;
import com.masai.expection.InputNotFound;
import com.masai.model.Candle;

public interface CandleInterface {

	public String addData() throws CandleClassException, IOException;

	public List<Candle> getCandlesData() throws InputNotFound;
	
	public String getFirstOrbCandleData(Integer time)throws CandleClassException,InputNotFound;

	public List<Candle> getCandlesNewInterval(Integer time)throws CandleClassException,InputNotFound;

	
}
