package tw.ouyang.recommendationservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"eureka.client.enabled=false"})
class RecommendationServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
