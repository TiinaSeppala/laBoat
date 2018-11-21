package app.laboat.repository;

import org.springframework.data.repository.CrudRepository;

import app.laboat.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
    
}