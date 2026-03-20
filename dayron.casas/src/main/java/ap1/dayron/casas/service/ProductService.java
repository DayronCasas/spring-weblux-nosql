package ap1.dayron.casas.service;

import ap1.dayron.casas.model.Product;
import ap1.dayron.casas.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Flux<Product> findAll() {
        return productRepository.findByDeletedAtIsNull();
    }

    public Flux<Product> findAllIncludingDeleted() {
        return productRepository.findAll();
    }

    public Flux<Product> findDeleted() {
        return productRepository.findByDeletedAtIsNotNull();
    }

    public Mono<Product> findById(String id) {
        return productRepository.findById(id);
    }

    public Mono<Product> create(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    public Mono<Product> update(String id, Product product) {
        return productRepository.findById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setCategoryId(product.getCategoryId());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setStock(product.getStock());
                    existingProduct.setCategory(product.getCategory());
                    existingProduct.setSku(product.getSku());
                    existingProduct.setBrand(product.getBrand());
                    existingProduct.setStatus(product.getStatus());
                    existingProduct.setUpdatedAt(LocalDateTime.now());
                    return productRepository.save(existingProduct);
                });
    }

    public Mono<Void> delete(String id) {
        return productRepository.deleteById(id);
    }

    public Mono<Product> softDelete(String id) {
        return productRepository.findById(id)
                .flatMap(product -> {
                    product.setDeletedAt(LocalDateTime.now());
                    product.setStatus("INACTIVE");
                    product.setUpdatedAt(LocalDateTime.now());
                    return productRepository.save(product);
                });
    }

    public Mono<Product> restore(String id) {
        return productRepository.findById(id)
                .flatMap(product -> {
                    product.setDeletedAt(null);
                    product.setStatus("ACTIVE");
                    product.setUpdatedAt(LocalDateTime.now());
                    return productRepository.save(product);
                });
    }

    public Flux<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public Flux<Product> findByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    public Mono<Product> findBySku(String sku) {
        return productRepository.findBySku(sku);
    }

    public Flux<Product> findActiveProducts() {
        return productRepository.findByStatusAndDeletedAtIsNull("ACTIVE");
    }

    public Flux<Product> findInactiveProducts() {
        return productRepository.findByStatusAndDeletedAtIsNull("INACTIVE");
    }

    public Flux<Product> findByCategoryId(String categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}
