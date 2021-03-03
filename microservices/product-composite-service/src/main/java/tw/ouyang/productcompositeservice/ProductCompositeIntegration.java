package tw.ouyang.productcompositeservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import tw.ouyang.utils.model.Product;
import tw.ouyang.utils.model.Recommendation;
import tw.ouyang.utils.model.Review;

@Component
public class ProductCompositeIntegration {

    @Value("${app.product-service.url}")
    private String productServiceUrl;

    @Value("${app.review-service.url}")
    private String reviewServiceUrl;

    @Value("${app.recommendation-service.url}")
    private String recommendationServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    public Product getProduct(int productId) {
        String url = productServiceUrl + productId;
        System.out.println(url);
        return restTemplate.getForObject(url, Product.class);
    }
    
    public List<Review> getReviews(int productId) {
        String url = reviewServiceUrl + productId;
        System.out.println(url);
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {}).getBody();
    }

    public List<Recommendation> getRecommendations(int productId) {
        String url = recommendationServiceUrl + productId;
        System.out.println(url);
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Recommendation>>() {}).getBody();
    }

}
