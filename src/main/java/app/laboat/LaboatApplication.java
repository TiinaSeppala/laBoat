package app.laboat;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.context.annotation.Bean;

import app.laboat.domain.Boat;
import app.laboat.domain.BoatRepository;
import app.laboat.domain.Type;
import app.laboat.domain.TypeRepository;
import app.laboat.domain.User;
import app.laboat.domain.UserRepository;

@SpringBootApplication(exclude = BatchAutoConfiguration.class)
public class LaboatApplication {
	private static final Logger log = LoggerFactory.getLogger(LaboatApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(LaboatApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner boatDemo(BoatRepository boatRepository, TypeRepository typeRepository, UserRepository userRepository) {
		return (args) -> {
			typeRepository.save(new Type("Power boat"));
			typeRepository.save(new Type("Sail boat"));
			typeRepository.save(new Type("Unpowered boat"));
			typeRepository.save(new Type("Personal watercraft"));
			
			boatRepository.save(new Boat("Amelia", "24", 2007, typeRepository.findByTypeName("Sail boat").get(0), 24000, "Porvoo", 7.6, 2.1));
			boatRepository.save(new Boat("Hobi", "Kona Deluxe", 2017, typeRepository.findByTypeName("Personal watercraft").get(0), 1200, "Helsinki", 5.0, 0.5));	
			
			for (Boat boat : boatRepository.findAll()) {
				log.info(boat.toString());
			}
			
			User user1 = new User("user","$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin","$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);

		};
}
}
