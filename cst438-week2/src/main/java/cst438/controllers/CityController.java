package cst438.controllers;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import cst438.CityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import cst438.domain.City;
import cst438.domain.CityRepository;
import cst438.domain.CityWeather;
import cst438.domain.TempAndTime;
import cst438.services.CityService;
import cst438.services.WeatherService;

@Controller
public class CityController {
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	WeatherService weatherService;
	
	@Autowired
	CityService cityService;
	

	@GetMapping("/cities/{city}")
	public String getCityInfo(@PathVariable("city") String name, Model model) {
		List<City> cities = cityRepository.findByName(name);
		TempAndTime cityWeather = cityService.getWeather(name);
//		Time time = TempAndTime.getTime(name);
		
		
//		city.setWeather(cityWeather);
//		 CityWeather cityWeather = cityService.getWeather(name);
//		 City.setWeather(cityWeather);
//		CityWeather cityWeather = weatherService.getWeather(name);
//		double tempF = Math.round((cityWeather.getTemp() - 273.15) * 9.0/5.0 + 32.0); 
		
//		cityWeather.setTemp(tempF);
		if (cities.size() != 0)
		{
		City city = cities.get(0);
		city.setWeather(cityWeather);
		model.addAttribute("cities", cities);
		return "city"; 
		}
		else
		{
		throw new ResponseStatusException( HttpStatus.NOT_FOUND, "The city "+name+" not found." );
		}
	}
}