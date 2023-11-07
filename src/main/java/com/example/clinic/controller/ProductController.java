package com.example.clinic.controller;
import com.example.clinic.models.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@Api(tags = "Produtos")
public class ProductController {

    private List<Product> products = new ArrayList<>();
    private Long idCounter = 1L;

    @GetMapping("/")
    @ApiOperation("Obtenha a lista de produtos")
    public List<Product> getAllProducts() {
        return products;
    }

    @GetMapping("/{id}")
    @ApiOperation("Obtenha um produto por ID")
    public Product getProductById(@PathVariable Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/")
    @ApiOperation("Crie um novo produto")
    public Product createProduct(@RequestBody Product product) {
        product.setId(idCounter++);
        products.add(product);
        return product;
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualize um produto existente")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        for (int i = 0; i < products.size(); i++) {
            Product existingProduct = products.get(i);
            if (existingProduct.getId().equals(id)) {
                products.set(i, product);
                product.setId(id);
                return product;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclua um produto por ID")
    public void deleteProduct(@PathVariable Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}