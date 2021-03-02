package tw.ouyang.productcompositeservice;

import java.util.List;

public class ProductAggregate {

    private int productId;
    private String name;
    private int weight;
    private List<ReviewSummary> reviews;
    private List<RecommendationSummary> recommendations;
    private ServiceAddresses serviceAddresses;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<ReviewSummary> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewSummary> reviews) {
        this.reviews = reviews;
    }

    public List<RecommendationSummary> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<RecommendationSummary> recommendations) {
        this.recommendations = recommendations;
    }

    public ServiceAddresses getServiceAddresses() {
        return serviceAddresses;
    }

    public void setServiceAddresses(ServiceAddresses serviceAddresses) {
        this.serviceAddresses = serviceAddresses;
    }

}
