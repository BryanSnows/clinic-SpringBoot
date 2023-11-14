package com.example.clinic.controller;

import com.example.clinic.models.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Produtos", description = "Operações relacionadas a produtos")
public class ProductController {

    private List<Product> products = new ArrayList<>();
    private Long idCounter = 1L;

    @GetMapping("/")
    @Operation(summary = "Obtenha a lista de produtos", description = "Retorna todos os produtos disponíveis.")
    public List<Product> getAllProducts() {
        return products;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenha um produto por ID", description = "Retorna um produto específico com base no ID fornecido.")
    public Product getProductById(@PathVariable Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/")
    @Operation(summary = "Crie um novo produto", description = "Cria um novo produto com base nos dados fornecidos no corpo da solicitação.")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        product.setId(idCounter++);
        products.add(product);
        return product;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualize um produto existente", description = "Atualiza um produto existente com base no ID fornecido e nos dados no corpo da solicitação.")
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
    @Operation(summary = "Exclua um produto por ID", description = "Exclui um produto com base no ID fornecido.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}