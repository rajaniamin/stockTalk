package com.masai.model;

import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Candle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonProperty("LastTradeTime")
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime lastTradeTime;

    @JsonProperty("QuotationLot")
    @NotBlank
    @Positive(message = "QuotationLot must be a positive value")
    private Integer quotationLot;

    @JsonProperty("TradedQty")
    @NotNull
    @Positive(message = "TradedQty must be a positive value")
    private Long tradedQty;

    @JsonProperty("OpenInterest")
    @NotNull
    @PositiveOrZero(message = "OpenInterest must be a non-negative value")
    private Integer openInterest;

    @JsonProperty("Open")
    @NotNull
    @DecimalMax(value = "0.0", message = "Open must be a non-negative value")
    private Double open;
    
    @JsonProperty("High")
    @NotNull
    @DecimalMin(value = "0.0", message = "High must be a non-negative value")
    private Double high;
    
    @JsonProperty("Low")
    @NotNull
    @DecimalMin(value = "0.0", message = "Low must be a non-negative value")
    private Double low;
    
    @JsonProperty("Close")
    @NotNull
    @DecimalMin(value = "0.0", message = "Close must be a non-negative value")
    private Double close;
    
    public Candle() {
		// TODO Auto-generated constructor stub
	}

	public Candle(Integer id, LocalDateTime lastTradeTime, Integer quotationLot, Long tradedQty, Integer openInterest,
			Double open, Double high, Double low, Double close) {
		super();
		this.id = id;
		this.lastTradeTime = lastTradeTime;
		this.quotationLot = quotationLot;
		this.tradedQty = tradedQty;
		this.openInterest = openInterest;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
	}

	public Candle(LocalDateTime lastTradeTime, Integer quotationLot, Long tradedQty, Integer openInterest, Double open,
			Double high, Double low, Double close) {
		super();
		this.lastTradeTime = lastTradeTime;
		this.quotationLot = quotationLot;
		this.tradedQty = tradedQty;
		this.openInterest = openInterest;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getLastTradeTime() {
		return lastTradeTime;
	}

	public void setLastTradeTime(LocalDateTime lastTradeTime) {
		this.lastTradeTime = lastTradeTime;
	}

	public Integer getQuotationLot() {
		return quotationLot;
	}

	public void setQuotationLot(Integer quotationLot) {
		this.quotationLot = quotationLot;
	}

	public Long getTradedQty() {
		return tradedQty;
	}

	public void setTradedQty(Long tradedQty) {
		this.tradedQty = tradedQty;
	}

	public Integer getOpenInterest() {
		return openInterest;
	}

	public void setOpenInterest(Integer openInterest) {
		this.openInterest = openInterest;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}
    
    

}
