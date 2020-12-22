package com.pc1crt.groceries.service;

import com.pc1crt.groceries.model.Categories;
import com.pc1crt.groceries.model.Product;
import com.pc1crt.groceries.repository.CategoriesRepository;
import com.pc1crt.groceries.repository.ProductRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoriesService categoriesService;

	public Product findById(Integer id) {

		return productRepository.findByProductId(id);
	}
	
	public List<Categories>addCategories() {
		return categoriesService.getAllCategories();
	}
	public void save(Product product) {
		productRepository.save(product);
	}
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	public void delete(Integer id) {
		productRepository.deleteById(id);
	}

	public List<Product> findByProduct(Integer id) {
		
		return productRepository.findByCategoryCategoriesId(id);
	}
	/*public void saveImage(MultipartFile imageFile, Product product) throws Exception {
		Path absolutePath = Paths.get(".");

		byte[] bytes = imageFile.getBytes();
		Path path = Paths.get(absolutePath + "/src/main/resources/static/photo/" + imageFile.getOriginalFilename());
		Files.write(path, bytes);
		product.setImage(bytes);
		save(product);
		*/

	public Object search(String searchTerm) {
		return productRepository.findByNameContaining(searchTerm);
		
	}

	public void decreaseQuantity(Integer id, int quantity) {
		Product product = new Product();
		product = this.productRepository.findByProductId(id);
		product.setStockQuantity(product.getStockQuantity() - quantity );
		this.productRepository.save(product);
		
		
	}
	
	
		
	
}
