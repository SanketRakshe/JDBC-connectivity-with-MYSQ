package com.example.restapiwithh2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restapiwithh2.model.Product;
import com.example.restapiwithh2.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	public ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	@Override
	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);
	}
	
	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	
	@Override
	public Product updateProduct(Long id, Product product) {
		Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setPrice(product.getPrice());
            return productRepository.save(updatedProduct);
        }
        return null;
	}
	
	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
