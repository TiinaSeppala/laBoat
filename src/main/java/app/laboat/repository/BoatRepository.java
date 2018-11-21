package app.laboat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.laboat.domain.Boat;

public interface BoatRepository extends CrudRepository<Boat, Long> {
	
	List<Boat> findByName(String name);

}
