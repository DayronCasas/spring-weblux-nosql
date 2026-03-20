package ap1.dayron.casas.repository;

import ap1.dayron.casas.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<Product> findByCategory(String category);

    Flux<Product> findByBrand(String brand);

    Mono<Product> findBySku(String sku);

    Flux<Product> findByCategoryId(String categoryId);

    Flux<Product> findByStatusAndDeletedAtIsNull(String status);

    Flux<Product> findByDeletedAtIsNull();

    Flux<Product> findByDeletedAtIsNotNull();
}
