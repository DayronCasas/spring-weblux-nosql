package ap1.dayron.casas.repository;

import ap1.dayron.casas.model.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {

    Mono<Category> findByCode(String code);

    Flux<Category> findByStatus(String status);

    Mono<Category> findByName(String name);

    Flux<Category> findByDeletedAtIsNull();

    Flux<Category> findByDeletedAtIsNotNull();
}
