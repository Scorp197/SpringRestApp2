package cst438.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;

import cst438.domain.City;
import cst438.domain.CityRepository;
import cst438.domain.CityWeather;
import cst438.domain.Country;
import cst438.services.CityService;
import cst438.services.WeatherService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

// class must be annotated as WebMvcTest,  not SpringBootTest
@WebMvcTest(CityRestController.class)
public class CityRestControllerTest {
	
	// declare as @MockBean those classes which will be stubbed in the test
	// These classes must be Spring components (such as Repositories)
	// or @Service classes.

	@MockBean
	private WeatherService weatherService;

	@MockBean
	private CityRepository cityRepository;
	
	@MockBean
	private CityService cityService;
	

	// This class is used for make simulated HTTP requests to the class
	// being tested.
	@Autowired
	private MockMvc mvc;
	
	// These objects will be magically initialized by the initFields method below.
    private JacksonTester<City> jsonCityAttempt;

    // This method is executed before each Test.
	@BeforeEach
	public void setUpEach() {
		MockitoAnnotations.initMocks(this);
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	
	@Test
	void contextLoads() {
	}

	// Have one or more test methods. 
	@Test
	public void cityRestControllerTest() throws Exception {
		
//////		Country country = new Country("TST", "Test Country");
		
		City city = new City();
		city.setName("TestCity");
		List<City> cities = new ArrayList<City>();
		cities.add(city);
		
//		Country country = new Country("TST", "Test Country");
//		City city = new City(1, "TestCity", "DistrictTest", 100000, country);
//		List<City> cities = new ArrayList<City>();
//		cities.add(city);
		

		// create the stub calls and return data for weather service
		//  when the getWeather method is called with name parameter "TestCity", 
		//  the stub will return the given temp (in degrees Kelvin) and condition.
		given(weatherService.getWeather("TestCity")).willReturn(new CityWeather("cloudy"));
		
		
		// this is the stub for the CityRepository.  When given input parm of "TestCity", 
		// it will return a list of cities.
		given(cityRepository.findByName("TestCity")).willReturn(cities);

		// perform the test by making simulated HTTP get using URL of "/city/TestCity"
		MockHttpServletResponse response = mvc.perform(get("/city/TestCity"))
				.andReturn().getResponse();
				

		// verify that result is as expected
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		// convert returned data from JSON string format to City object
		City cityResult = jsonCityAttempt.parseObject(response.getContentAsString());
		
		City expectedResult = new City("TestCity");
		// expected weather temp is in degrees F, not Kelvin.
		expectedResult.setWeather(new CityWeather("cloudy"));
		
		// compare actual return data with expected data
	    // MUST implement .equals( ) method for City class.
		assertThat(cityResult).isEqualTo(expectedResult);
		
	}

}