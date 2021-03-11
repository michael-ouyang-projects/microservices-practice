package tw.ouyang.productcompositeservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import tw.ouyang.utils.model.Product;
import tw.ouyang.utils.model.Recommendation;
import tw.ouyang.utils.model.Review;

@Component
public class ProductCompositeIntegration {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private WebClient.Builder loadBalancedWebClientBuilder;

    private WebClient loadBalancedWebClient;

    @Autowired
    private RestTemplate restTemplate;

    public Product getProduct(int productId) {
        String url = String.format("%s/product/%d", discoveryClient.getInstances("product").get(0).getUri().toString(), productId);
        return restTemplate.getForObject(url, Product.class);
    }

    public List<Review> getReviews(int productId) {
        String url = String.format("%s/reviews?productId=%d", discoveryClient.getInstances("review").get(0).getUri().toString(), productId);
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
        }).getBody();
    }

    public List<Recommendation> getRecommendations(int productId) {
        String url = String.format("%s/recommendations?productId=%d", discoveryClient.getInstances("recommendation").get(0).getUri().toString(), productId);
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Recommendation>>() {
        }).getBody();
    }

    private WebClient getLoadBalancedWebClient() {
        if (loadBalancedWebClient == null) {
            loadBalancedWebClient = loadBalancedWebClientBuilder.build();
        }
        return loadBalancedWebClient;
    }

}
