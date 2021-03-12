package tw.ouyang.productcompositeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tw.ouyang.utils.model.Product;
import tw.ouyang.utils.model.Recommendation;
import tw.ouyang.utils.model.Review;

@Component
public class ProductCompositeIntegration {

    @Autowired
    private WebClient.Builder loadBalancedWebClientBuilder;

    private WebClient loadBalancedWebClient;

    @Value("${app.product-service.url}")
    private String productServiceUrl;

    @Value("${app.review-service.url}")
    private String reviewServiceUrl;

    @Value("${app.recommendation-service.url}")
    private String recommendationServiceUrl;

    public Mono<Product> getProduct(int productId) {
        String url = productServiceUrl + productId;
        return getLoadBalancedWebClient().get().uri(url).retrieve().bodyToMono(Product.class);
    }

    public Flux<Review> getReviews(int productId) {
        String url = reviewServiceUrl + productId;
        return getLoadBalancedWebClient().get().uri(url).retrieve().bodyToFlux(Review.class);
    }

    public Flux<Recommendation> getRecommendations(int productId) {
        String url = recommendationServiceUrl + productId;
        return getLoadBalancedWebClient().get().uri(url).retrieve().bodyToFlux(Recommendation.class);
    }

    private WebClient getLoadBalancedWebClient() {
        if (loadBalancedWebClient == null) {
            loadBalancedWebClient = loadBalancedWebClientBuilder.build();
        }
        return loadBalancedWebClient;
    }

}
