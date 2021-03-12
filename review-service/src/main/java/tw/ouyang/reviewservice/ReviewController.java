package tw.ouyang.reviewservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import tw.ouyang.utils.http.ServiceUtil;
import tw.ouyang.utils.model.Review;

@RestController
public class ReviewController {

    @Autowired
    private ServiceUtil serviceUtil;

    @GetMapping("/reviews")
    public Flux<Review> getReviews(@RequestParam int productId) {
        return Flux.just(
                new Review(productId, 1, "Author 1", "Subject 1", "Content 1", serviceUtil.getServiceAddress()),
                new Review(productId, 2, "Author 2", "Subject 2", "Content 2", serviceUtil.getServiceAddress()),
                new Review(productId, 3, "Author 3", "Subject 3", "Content 3", serviceUtil.getServiceAddress()));
    }

}
