package com.pc1crt.groceries.repository;

import com.pc1crt.groceries.model.Categories;



import org.springframework.data.jpa.repository.JpaRepository;




public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

	public Categories findByCategoriesId(Integer id);


}
