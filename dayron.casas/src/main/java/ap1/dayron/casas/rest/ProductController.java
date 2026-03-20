package ap1.dayron.casas.rest;

import ap1.dayron.casas.model.Product;
import ap1.dayron.casas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Product> findById(@PathVariable String id) {
        return productService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Product> update(@PathVariable String id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return productService.delete(id);
    }

    @PatchMapping("/{id}/soft-delete")
    public Mono<Product> softDelete(@PathVariable String id) {
        return productService.softDelete(id);
    }

    @PatchMapping("/{id}/restore")
    public Mono<Product> restore(@PathVariable String id) {
        return productService.restore(id);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Product> findAllIncludingDeleted() {
        return productService.findAllIncludingDeleted();
    }

    @GetMapping(value = "/deleted", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Product> findDeleted() {
        return productService.findDeleted();
    }

    @GetMapping(value = "/category/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Product> findByCategory(@PathVariable String category) {
        return productService.findByCategory(category);
    }

    @GetMapping(value = "/brand/{brand}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Product> findByBrand(@PathVariable String brand) {
        return productService.findByBrand(brand);
    }

    @GetMapping(value = "/sku/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Product> findBySku(@PathVariable String sku) {
        return productService.findBySku(sku);
    }

    @GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Product> findActiveProducts() {
        return productService.findActiveProducts();
    }

    @GetMapping(value = "/inactive", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Product> findInactiveProducts() {
        return productService.findInactiveProducts();
    }

    @GetMapping(value = "/category-id/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Product> findByCategoryId(@PathVariable String categoryId) {
        return productService.findByCategoryId(categoryId);
    }
}
