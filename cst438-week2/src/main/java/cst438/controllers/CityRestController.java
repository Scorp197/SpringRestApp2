package cst438.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cst438.domain.City;
import cst438.domain.CityRepository;
import cst438.domain.CityWeather;
import cst438.domain.TempAndTime;
import cst438.services.CityService;
import cst438.services.WeatherService;

@RestController
public class CityRestController {
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	WeatherService weatherService;
	
	@Autowired
	CityService cityService;
	
	@GetMapping("/api/cities/{name}")
	public ResponseEntity<City> cityInfo(@PathVariable("name") String name ) {
		
		// look up city info from database.  Might be multiple cities with same name.
		List<City> cities = cityRepository.findByName(name);
		if ( cities.size()==0) {
			
			// city name not found.  Send 404 return code.
			return new ResponseEntity<City>( HttpStatus.NOT_FOUND);
			
		} else {
			
			// in case of multiple cities, take the first one.
		    City city=cities.get(0);
		    
		    // get current weather
			// convert temp from degrees Kelvin to degrees Fahrenheit
			 TempAndTime cityWeather = cityService.getWeather(name);
			 city.setWeather(cityWeather);
			
			// return 200 status code (OK) and city information in JSON format
			return new ResponseEntity<City>(city, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/api/city/{name}")
	public ResponseEntity<City> deleteCity(@PathVariable("name") String name ) {
		
		// look up city info from database.  Might be multiple cities with same name.
		List<City> cities = cityRepository.findByName(name);
		if ( cities.size()==0) {
			// city name not found.  Send 404 return code.
			return new ResponseEntity<City>( HttpStatus.NOT_FOUND);
		} else {
			for (City c : cities) {
				cityRepository.delete(c);
			}
			// return 204,  request successful.  no content returned.
			return new ResponseEntity<City>( HttpStatus.NO_CONTENT);
		}
	}
	
}