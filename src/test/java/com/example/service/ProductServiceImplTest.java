package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
	
	@Test
    public void testAddProduct() {
        Product product = new Product("New Product", 300);
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.addProduct(product);
        assertNotNull(savedProduct);
        assertEquals("New Product", savedProduct.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateProduct() {
        Product existingProduct = new Product("Old Product", 100);
        Product updatedProduct = new Product("Updated Product", 150);

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        Product result = productService.updateProduct(1L, updatedProduct);
        assertEquals("Updated Product", result.getName());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    public void testDeleteProduct() {
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
	
}
