package com.example.keycloak.product;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;


    @PostMapping
    public Map<String, String> create(@RequestBody ProductRequest request) {
        return productService.create(request);
    }


    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }


    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }


    @PutMapping("/{id}")
    public Map<String, String> update(@PathVariable Long id, @RequestBody ProductRequest request) {
        return productService.updateProduct(id, request);
    }


    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

}
