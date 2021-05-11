package cst438;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import cst438.CityService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CityController {
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	WeatherService weatherService;

	@GetMapping("/cities/{city}")
	public String getCityInfo(@PathVariable("city") String name, Model model) {
		List<City> cities = cityRepository.findByName(name);
//		CityWeather cityWeather = weatherService.getWeather(name);
		model.addAttribute("cities", cities);
		return "city"; 
	}
}
	
//	@PostMapping
//	public String getCityInfo(@PathVariable("city") String name, Model model) {
//	CityWeather cityWeather = weatherService.getWeather(name);
//}
//


//String cityName, String code, String name, 
//String district, int population, int condition, int temp
