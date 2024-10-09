package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.restapiwithh2.model.Product;
import com.example.restapiwithh2.repository.ProductRepository;
import com.example.restapiwithh2.service.ProductServiceImpl;

public class ProductServiceImplTest {
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Mock
	private ProductRepository productRepository;
	
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);  // Initialize mocks
    }
	
	@Test
	public void testGetAllProducts() {
		Product product1 = new Product("Product 1", 100);
		Product product2 = new Product("Product 2", 200);
		
		when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
		
		assertEquals(2, productService.getAllProducts().size());
		verify(productRepository, times(1)).findAll();
	}
	
	@Test
	public void testGetProductById() {
		Product product = new Product("Product 1", 100);
		
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		
		Optional<Product> result = productService.getProductById(1L);
		
		assertTrue(result.isPresent());
        assertEquals("Product 1", result.get().getName());
        verify(productRepository, times(1)).findById(1L);
	}
	
}
