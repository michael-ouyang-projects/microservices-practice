package tw.ouyang.reviewservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.ouyang.utils.http.ServiceUtil;
import tw.ouyang.utils.model.Review;

@RestController
public class ReviewController {

    @Autowired
    private ServiceUtil serviceUtil;

    @GetMapping("/reviews")
    public List<Review> getReviews(@RequestParam int productId) {
        List<Review> list = new ArrayList<>();
        list.add(new Review(productId, 1, "Author 1", "Subject 1", "Content 1", serviceUtil.getServiceAddress()));
        list.add(new Review(productId, 2, "Author 2", "Subject 2", "Content 2", serviceUtil.getServiceAddress()));
        list.add(new Review(productId, 3, "Author 3", "Subject 3", "Content 3", serviceUtil.getServiceAddress()));
        return list;
    }

}
