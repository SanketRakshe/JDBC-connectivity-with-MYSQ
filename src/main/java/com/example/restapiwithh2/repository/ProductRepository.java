package com.example.restapiwithh2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restapiwithh2.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
