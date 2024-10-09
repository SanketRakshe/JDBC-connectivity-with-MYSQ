package com.example.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.restapiwithh2.controller.ProductController;
import com.example.restapiwithh2.model.Product;
import com.example.restapiwithh2.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Product product1 = new Product("Product 1", 100);
        Product product2 = new Product("Product 2", 200);
        when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andDo(print());

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product("Product 1", 100);
        when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/api/products/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Product 1"))
            .andDo(print());

        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    public void testAddProduct() throws Exception {
        Product product = new Product("New Product", 300);
        when(productService.addProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(product)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("New Product"))
            .andDo(print());

        verify(productService, times(1)).addProduct(any(Product.class));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product("Updated Product", 150);
        when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(product);

        mockMvc.perform(put("/api/products/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(product)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Updated Product"))
            .andDo(print());

        verify(productService, times(1)).updateProduct(eq(1L), any(Product.class));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/1"))
            .andExpect(status().isNoContent())
            .andDo(print());

        verify(productService, times(1)).deleteProduct(1L);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
