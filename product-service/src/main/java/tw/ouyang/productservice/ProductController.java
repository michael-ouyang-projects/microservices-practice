package tw.ouyang.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import tw.ouyang.utils.http.ServiceUtil;
import tw.ouyang.utils.model.Product;

@RestController
public class ProductController {

    @Autowired
    private ServiceUtil serviceUtil;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product/{productId}")
    public Mono<Product> getProduct(@PathVariable int productId) {
        ProductEntity productEntity = productRepository.findByProductId(productId).orElseGet(() -> {
            ProductEntity t = new ProductEntity();
            t.setProductId(99999);
            t.setName("default");
            t.setWeight(99999);
            return t;
        });
        Product product = new Product();
        product.setProductId(productEntity.getProductId());
        product.setName(productEntity.getName());
        product.setWeight(productEntity.getWeight());
        product.setServiceAddress(serviceUtil.getServiceAddress());
        return Mono.just(product);
    }

    @PostMapping("/product")
    public void createProduct(@RequestBody Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(product.getProductId());
        productEntity.setName(product.getName());
        productEntity.setWeight(product.getWeight());
        productRepository.save(productEntity);
    }

    @DeleteMapping("/product/{productId}")
    public void deleteProduct(@PathVariable int productId) {
        productRepository.deleteByProductId(productId);
    }

}
