package com.pc1crt.groceries.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.pc1crt.groceries.model.Customer;
import com.pc1crt.groceries.model.Product;
import com.pc1crt.groceries.repository.CustomerRepository;
import com.pc1crt.groceries.repository.ProductRepository;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {
	@Mock
	private ProductService productService;
	@Mock
	private ProductRepository productRepository;
	
	

	
	@Test
	void testFindAll() {
		Product product = new Product("test", "test", 0.00, "test", "test", 5, null, null, null);
		product.setproductId(1);
		
		when(productRepository.findByProductId(anyInt())).thenReturn(product);
		
		Product result = productService.findById(1);
		assertThat("result",result,is(sameInstance(product)));

	}
	/*@Test
	void testServiceSave() {
		Product product = new Product("test", "test", 0.00, "test", "test", 5, null, null, null);
		product.setproductId(4);
		productService.save(product);
		System.out.println(product);
		System.out.println(productRepository.findById(4));
	}*/

}
