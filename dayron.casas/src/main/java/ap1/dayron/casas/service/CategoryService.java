package ap1.dayron.casas.service;

import ap1.dayron.casas.model.Category;
import ap1.dayron.casas.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Flux<Category> findAll() {
        return categoryRepository.findByDeletedAtIsNull();
    }

    public Flux<Category> findAllIncludingDeleted() {
        return categoryRepository.findAll();
    }

    public Flux<Category> findDeleted() {
        return categoryRepository.findByDeletedAtIsNotNull();
    }

    public Mono<Category> findById(String id) {
        return categoryRepository.findById(id);
    }

    public Mono<Category> create(Category category) {
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    public Mono<Category> update(String id, Category category) {
        return categoryRepository.findById(id)
                .flatMap(existingCategory -> {
                    existingCategory.setName(category.getName());
                    existingCategory.setDescription(category.getDescription());
                    existingCategory.setCode(category.getCode());
                    existingCategory.setOrder(category.getOrder());
                    existingCategory.setStatus(category.getStatus());
                    existingCategory.setUpdatedAt(LocalDateTime.now());
                    return categoryRepository.save(existingCategory);
                });
    }

    public Mono<Void> delete(String id) {
        return categoryRepository.deleteById(id);
    }

    public Mono<Category> softDelete(String id) {
        return categoryRepository.findById(id)
                .flatMap(category -> {
                    category.setDeletedAt(LocalDateTime.now());
                    category.setStatus("INACTIVE");
                    category.setUpdatedAt(LocalDateTime.now());
                    return categoryRepository.save(category);
                });
    }

    public Mono<Category> restore(String id) {
        return categoryRepository.findById(id)
                .flatMap(category -> {
                    category.setDeletedAt(null);
                    category.setStatus("ACTIVE");
                    category.setUpdatedAt(LocalDateTime.now());
                    return categoryRepository.save(category);
                });
    }

    public Mono<Category> findByCode(String code) {
        return categoryRepository.findByCode(code);
    }

    public Flux<Category> findActiveCategories() {
        return categoryRepository.findByStatus("ACTIVE");
    }

    public Mono<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
