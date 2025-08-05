package com.example.keycloak.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    Map<String,String> create(ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.productName());
        product.setDescription(productRequest.desc());
        product.setPrice(productRequest.price());
        productRepository.save(product);

        return Map.of("msg","successfully insert product");
    }

    // ✅ List all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // ✅ Get product by ID
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + id));
    }

    // ✅ Update product
    public Map<String, String> updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + id));

        product.setName(request.productName());
        product.setDescription(request.desc());
        product.setPrice(request.price());

        productRepository.save(product);

        return Map.of("msg", "Product updated successfully");
    }

    // ✅ Delete product
    public Map<String, String> deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + id));

        productRepository.delete(product);
        return Map.of("msg", "Product deleted successfully");
    }

}
