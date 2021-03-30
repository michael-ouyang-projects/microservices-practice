package tw.ouyang.recommendationservice;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tw.ouyang.utils.http.ServiceUtil;
import tw.ouyang.utils.model.Recommendation;

@RestController
public class RecommendationController {

    @Autowired
    private ServiceUtil serviceUtil;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @GetMapping("/recommendations")
    public Flux<Recommendation> getRecommendations(@RequestParam int productId) {
        return Mono.just(recommendationRepository.findByProductId(productId)
                .stream()
                .map(entity -> {
                    Recommendation recommendation = new Recommendation();
                    recommendation.setProductId(entity.getProductId());
                    recommendation.setRecommendationId(entity.getRecommendationId());
                    recommendation.setAuthor(entity.getAuthor());
                    recommendation.setRate(entity.getRate());
                    recommendation.setContent(entity.getContent());
                    recommendation.setServiceAddress(serviceUtil.getServiceAddress());
                    return recommendation;
                }).collect(Collectors.toList())).flatMapMany(Flux::fromIterable);
    }

}
