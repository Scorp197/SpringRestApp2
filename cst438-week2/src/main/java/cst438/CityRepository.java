package cst438;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
	List<City>findByName(String name);

}



	
