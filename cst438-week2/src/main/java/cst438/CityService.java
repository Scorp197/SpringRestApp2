package cst438;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;


@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private WeatherService weatherService;
	
	public  String getWeather(String name) {
		CityWeather cityWeather = weatherService.getWeather(name);
		return "city";
	

	}
	
}

