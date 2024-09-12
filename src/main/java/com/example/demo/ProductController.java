package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    private final List<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(
                new Product(
                        "Chicken", 10,
                        new Product.ProductionLabel(
                                "PL123456", LocalDate.of(2024, 5, 22)
                        )
                )
        );
        products.add(
                new Product(
                        "Turnip", 3,
                        new Product.ProductionLabel(
                                "A random guy", LocalDate.of(2024, 5, 20)
                        )
                )
        );
    }

    @GetMapping("hello/{who}")
    String hello(@PathVariable String who) {
        return String.format("Hello %s", who);
    }

//    @GetMapping({"", "/","item"})
//    Product getItem() {
//        return product;
//    }

    @GetMapping("")
    List<Product> getItem() {
        return products;
    }

    @GetMapping("{index}")
    Product getItem(@PathVariable int index) {
        if (index < 0 || index >= products.size())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return products.get(index);
    }

    @PostMapping()
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        products.add(product);
        return new ResponseEntity<>("Product added successfully\n", HttpStatus.CREATED);
    }

    @PutMapping("{index}")
    public ResponseEntity<String> updateProduct(
            @PathVariable int index, @RequestBody Product updatedProduct
    ) {
        if (index < 0 || index >= products.size())
            return ResponseEntity.notFound().build();
        this.products.set(index, updatedProduct);
        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("{index}")
    public ResponseEntity<String> deleteProduct(@PathVariable int index) {
        if (index < 0 || index >= products.size())
            return ResponseEntity.notFound().build();
        products.remove(index);
        return ResponseEntity.ok("Product deleted successfully");
    }

}
