package com.masai.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Candle;

public interface CandleRepo extends JpaRepository<Candle, Integer>{
	public List<Candle> getFirstAndLastCandlesOfTheDay();
	List<Candle> findByLastTradeTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
	public List<Candle> findByLastTradeTimeAfter(LocalDateTime endDate);

}
	