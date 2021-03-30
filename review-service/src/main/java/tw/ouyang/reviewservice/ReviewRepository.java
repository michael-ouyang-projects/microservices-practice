package tw.ouyang.reviewservice;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<ReviewEntity, String> {

    public List<ReviewEntity> findByProductId(Integer productId);

}
