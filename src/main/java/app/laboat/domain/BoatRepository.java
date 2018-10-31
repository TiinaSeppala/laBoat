package app.laboat.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BoatRepository extends CrudRepository<Boat, Long> {
	
	List<Boat> findByName(String name);

}
