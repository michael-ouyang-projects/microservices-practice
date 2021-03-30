package tw.ouyang.reviewservice;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

@ComponentScan("tw.ouyang")
@SpringBootApplication
public class ReviewServiceApplication {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ReviewServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            String sqlStatements[] = {
                    "INSERT INTO REVIEWS(id, product_id, review_id, author, subject, content) VALUES('1', 99999, 1, 'Author 1', 'Subject 1', 'Content 1')",
                    "INSERT INTO REVIEWS(id, product_id, review_id, author, subject, content) VALUES('2', 99999, 2, 'Author 2', 'Subject 2', 'Content 2')",
                    "INSERT INTO REVIEWS(id, product_id, review_id, author, subject, content) VALUES('3', 99999, 3, 'Author 3', 'Subject 3', 'Content 3')"
            };

            Arrays.asList(sqlStatements).forEach(sql -> {
                jdbcTemplate.execute(sql);
            });
        };
    }

}
