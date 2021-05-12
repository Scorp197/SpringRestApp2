package cst438.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;

import cst438.domain.CityWeather;
import cst438.domain.TempAndTime;

@Service
public class WeatherService {
	
	private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

	private RestTemplate restTemplate;

	private String weatherUrl;
	private String apiKey;
	
	// retrieve url and apikey for weather service from application.properties file
	public WeatherService( 
		@Value("${weather.url}") final String weatherUrl,
		@Value("${weather.apikey}") final String apiKey ) {
	this.restTemplate = new RestTemplate();
	this.weatherUrl = "http://api.openweathermap.org/data/2.5/weather";
	this.apiKey = "7e52c313667d3d0b81bd332ddf513006";
	}
	
	public TempAndTime getWeather(String cityName) {
		ResponseEntity<JsonNode> response = restTemplate.getForEntity(
				weatherUrl + "?q=" + cityName + "&appid=" + apiKey,
				JsonNode.class);
		JsonNode json = response.getBody();
		log.info("Status code from weather server:" + response.getStatusCodeValue());
		double temp = json.get("main").get("temp").asDouble();
		long time = json.get("dt").asLong();
		int timezone = json.get("timezone").asInt();
		return new TempAndTime(temp, time, timezone);
//		String condition = json.get("weather").get(0).get("description").asText();
	
//		return new CityWeather(temp, condition, time, timezone );
		
		
	}
	
	private static  String adjustTime(int timezone, long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
		TimeZone tz = TimeZone.getTimeZone("UTC");
		tz.setRawOffset(timezone*1000);
		sdf.setTimeZone(tz);
		Date date = new Date(time*1000);
		return sdf.format(date);
	}
		
}