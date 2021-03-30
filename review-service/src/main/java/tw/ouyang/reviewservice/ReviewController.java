package tw.ouyang.reviewservice;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tw.ouyang.utils.http.ServiceUtil;
import tw.ouyang.utils.model.Review;

@RestController
public class ReviewController {

    @Autowired
    private ServiceUtil serviceUtil;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/reviews")
    public Flux<Review> getReviews(@RequestParam int productId) {
        return Mono.just(reviewRepository.findByProductId(productId)
                .stream()
                .map(entity -> {
                    Review review = new Review();
                    review.setProductId(entity.getProductId());
                    review.setReviewId(entity.getReviewId());
                    review.setAuthor(entity.getAuthor());
                    review.setSubject(entity.getSubject());
                    review.setContent(entity.getContent());
                    review.setServiceAddress(serviceUtil.getServiceAddress());
                    return review;
                }).collect(Collectors.toList())).flatMapMany(Flux::fromIterable);
    }

}
