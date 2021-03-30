package tw.ouyang.productservice;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, String> {

    public Optional<ProductEntity> findByProductId(int productId);

    public void deleteByProductId(int productId);

}
