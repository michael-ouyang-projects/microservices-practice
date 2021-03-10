package tw.ouyang.productcompositeservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tw.ouyang.utils.http.ServiceUtil;
import tw.ouyang.utils.model.Product;
import tw.ouyang.utils.model.Recommendation;
import tw.ouyang.utils.model.Review;

@Api("Product-Composite REST API")
@RestController
public class ProductCompositeController {

    @Autowired
    private ServiceUtil serviceUtil;

    @Autowired
    private ProductCompositeIntegration productCompositeIntegration;

    @ApiOperation("Fetch product's information by id.")
    @GetMapping("/product-composite/{productId}")
    public ProductAggregate getProductAggregate(@PathVariable int productId) {
        Product product = productCompositeIntegration.getProduct(productId);
        List<Review> reviews = productCompositeIntegration.getReviews(productId);
        List<Recommendation> recommendations = productCompositeIntegration.getRecommendations(productId);
        return createProductAggregate(product, reviews, recommendations);
    }

    private ProductAggregate createProductAggregate(Product product, List<Review> reviews, List<Recommendation> recommendations) {
        List<ReviewSummary> reviewSummaries = reviews.stream()
                .map(review -> new ReviewSummary(review.getReviewId(), review.getAuthor(), review.getSubject(), review.getContent()))
                .collect(Collectors.toList());

        List<RecommendationSummary> recommendationSummaries = recommendations.stream()
                .map(recommendation -> new RecommendationSummary(recommendation.getRecommendationId(), recommendation.getAuthor(), recommendation.getRate(), recommendation.getContent()))
                .collect(Collectors.toList());

        String productAddress = product.getServiceAddress();
        String reviewAddress = reviews.get(0).getServiceAddress();
        String recommendationAddress = recommendations.get(0).getServiceAddress();
        ServiceAddresses serviceAddresses = new ServiceAddresses(serviceUtil.getServiceAddress(), productAddress, reviewAddress, recommendationAddress);

        ProductAggregate productAggregate = new ProductAggregate();
        productAggregate.setProductId(product.getProductId());
        productAggregate.setName(product.getName());
        productAggregate.setWeight(product.getWeight());
        productAggregate.setReviews(reviewSummaries);
        productAggregate.setRecommendations(recommendationSummaries);
        productAggregate.setServiceAddresses(serviceAddresses);
        return productAggregate;
    }

}
