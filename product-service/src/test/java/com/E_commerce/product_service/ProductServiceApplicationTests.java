package com.E_commerce.product_service;

import com.E_commerce.product_service.DTO.ProductRequest;
import com.E_commerce.product_service.Model.Product;
import com.E_commerce.product_service.Repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mongodb.MongoDBContainer;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer=new MongoDBContainer("mongo:8.0.4");

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeEach
    void setup(){

        productRepository.deleteAll();
    }
	@Test
	void testCreateProduct() throws Exception {
        ProductRequest productRequest=getProductRequest();
        String productString=objectMapper.writeValueAsString(productRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productString)
        ).andExpect(status().isCreated());
        Assertions.assertEquals(1,productRepository.findAll().size());
	}

    private ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .name("Laptop")
                .skuCode("Laptop")
                .description("Laptop")
                .price(BigDecimal.valueOf(50000))
                .build();
    }

    @Test
    void testGetProduct() throws Exception {
        Product product = Product.builder()
                .name("Laptop")
                .description("Laptop")
                .skuCode("Laptop")
                .price(BigDecimal.valueOf(50000))
                .build();
        productRepository.save(product);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertEquals(1,productRepository.findAll().size());
    }

}
