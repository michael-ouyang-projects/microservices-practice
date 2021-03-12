package tw.ouyang.recommendationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import tw.ouyang.utils.http.ServiceUtil;
import tw.ouyang.utils.model.Recommendation;

@RestController
public class RecommendationController {

    @Autowired
    private ServiceUtil serviceUtil;

    @GetMapping("/recommendations")
    public Flux<Recommendation> getRecommendations(@RequestParam int productId) {
        return Flux.just(
                new Recommendation(productId, 1, "Author 1", 1, "Content 1", serviceUtil.getServiceAddress()),
                new Recommendation(productId, 2, "Author 2", 2, "Content 2", serviceUtil.getServiceAddress()),
                new Recommendation(productId, 3, "Author 3", 3, "Content 3", serviceUtil.getServiceAddress()));
    }

}
