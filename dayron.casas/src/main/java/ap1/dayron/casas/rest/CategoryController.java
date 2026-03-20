package ap1.dayron.casas.rest;

import ap1.dayron.casas.model.Category;
import ap1.dayron.casas.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Category> findById(@PathVariable String id) {
        return categoryService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Category> create(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Category> update(@PathVariable String id, @RequestBody Category category) {
        return categoryService.update(id, category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return categoryService.delete(id);
    }

    @PatchMapping("/{id}/soft-delete")
    public Mono<Category> softDelete(@PathVariable String id) {
        return categoryService.softDelete(id);
    }

    @PatchMapping("/{id}/restore")
    public Mono<Category> restore(@PathVariable String id) {
        return categoryService.restore(id);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Category> findAllIncludingDeleted() {
        return categoryService.findAllIncludingDeleted();
    }

    @GetMapping(value = "/deleted", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Category> findDeleted() {
        return categoryService.findDeleted();
    }

    @GetMapping(value = "/code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Category> findByCode(@PathVariable String code) {
        return categoryService.findByCode(code);
    }

    @GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Category> findActiveCategories() {
        return categoryService.findActiveCategories();
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Category> findByName(@PathVariable String name) {
        return categoryService.findByName(name);
    }
}
