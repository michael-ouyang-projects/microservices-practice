package tw.ouyang.recommendationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;

@ComponentScan("tw.ouyang")
@SpringBootApplication
public class RecommendationServiceApplication {

    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RecommendationServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            mongoTemplate.save(new RecommendationEntity(99999, 1, "Author 1", 1, "Content 1"), "recommendations");
            mongoTemplate.save(new RecommendationEntity(99999, 2, "Author 2", 2, "Content 2"), "recommendations");
            mongoTemplate.save(new RecommendationEntity(99999, 3, "Author 3", 3, "Content 3"), "recommendations");
        };
    }

}
