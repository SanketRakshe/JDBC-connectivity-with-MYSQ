package com.example.restapiwithh2.service;

import java.util.List;
import java.util.Optional;

import com.example.restapiwithh2.model.Product;

public interface ProductService {
	List<Product> getAllProducts();
	Optional<Product> getProductById(Long id);
	Product addProduct(Product product);
	Product updateProduct(Long id, Product product);
	void deleteProduct(Long id);
}
