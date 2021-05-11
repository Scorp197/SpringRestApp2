//package cst438;
//
//import java.util.List;
//
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//
//public class CityInfo {
//	
//	public String getCityInfo(@PathVariable("city") String name, Model model) {
//		List<City> cities = cityRepository.findByName(name);
////		CityWeather cityWeather = weatherService.getWeather(name);
//		model.addAttribute("cities", cities);
//		return "city"; 
//		
//}
