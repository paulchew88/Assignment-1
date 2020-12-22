package com.pc1crt.groceries.repository;

import java.util.List;
import java.util.UUID;

import com.pc1crt.groceries.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	
	public Product findByProductId(Integer id);
	public List<Product> findByNameContaining(String searchTerm); 
	public List<Product> findBySkuContaining(String searchTerm);
	public List<Product> findByCategoryCategoriesId(Integer id);

}
