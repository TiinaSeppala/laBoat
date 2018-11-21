package app.laboat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.laboat.domain.Type;

public interface TypeRepository extends CrudRepository<Type, Long>{
	
	List<Type> findByTypeName(String typeName);
	
}
