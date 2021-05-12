package cst438.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import cst438.domain.CityRepository;
import cst438.domain.CityWeather;
import cst438.domain.CountryRepository;
import cst438.domain.TempAndTime;


@Service
public class CityService {
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private WeatherService weatherService;
	
//	public WeatherService getWeatherService() {
//		return weatherService;
//	}
//
//	public void setWeatherService(WeatherService weatherService) {
//		this.weatherService = weatherService;
//	}

	
	public TempAndTime getWeather(String name) {
		TempAndTime cityWeather = weatherService.getWeather(name);
		double tempF = Math.round((cityWeather.getTemp() - 273.15) * 9.0/5.0 + 32.0); 
		cityWeather.setTemp(tempF);
	          return cityWeather;
	

			
	}
	
}

