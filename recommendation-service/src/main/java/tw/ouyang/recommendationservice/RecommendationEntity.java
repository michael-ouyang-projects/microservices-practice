package tw.ouyang.recommendationservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recommendations")
@CompoundIndex(def = "{'productId': 1, 'recommendationId': 1}", unique = true, name = "recommendations-index-productId-recommendationId")
public class RecommendationEntity {

    @Id
    private String id;
    private Integer productId;
    private Integer recommendationId;
    private String author;
    private Integer rate;
    private String content;

    public RecommendationEntity(Integer productId, Integer recommendationId, String author, Integer rate, String content) {
        this.productId = productId;
        this.recommendationId = recommendationId;
        this.author = author;
        this.rate = rate;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(Integer recommendationId) {
        this.recommendationId = recommendationId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
