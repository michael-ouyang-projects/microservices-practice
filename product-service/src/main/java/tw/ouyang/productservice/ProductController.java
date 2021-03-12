package tw.ouyang.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import tw.ouyang.utils.http.ServiceUtil;
import tw.ouyang.utils.model.Product;

@RestController
public class ProductController {

    @Autowired
    private ServiceUtil serviceUtil;

    @GetMapping("/product/{productId}")
    public Mono<Product> getProduct(@PathVariable int productId) {
        return Mono.just(new Product(productId, "name-" + productId, 100, serviceUtil.getServiceAddress()));
    }

}
