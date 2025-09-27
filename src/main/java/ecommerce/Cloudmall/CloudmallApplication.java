package ecommerce.Cloudmall;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class CloudmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudmallApplication.class, args);
	}

	// This bean will run at startup and test MongoDB connection
	@Bean
	CommandLineRunner testMongoConnection(MongoTemplate mongoTemplate) {
		return args -> {
			try {
				// Perform a simple operation to trigger the connection
				System.out.println("MongoDB connected to: " + mongoTemplate.getDb().getName());
			} catch (Exception e) {
				System.out.println("MongoDB connection failed: " + e.getMessage());
			}
		};
	}
}

