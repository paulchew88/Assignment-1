package com.pc1crt.groceries.service;

import com.pc1crt.groceries.model.Categories;

import com.pc1crt.groceries.repository.CategoriesRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.io.*;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class CategoriesService {
	@Autowired
	private CategoriesRepository categoriesRepository;

	public List<Categories> getAllCategories() {
		return categoriesRepository.findAll();
	}

	public void deleteAll() {
		categoriesRepository.deleteAll();
	}

	public void save(Categories categories) {
		this.categoriesRepository.save(categories);
	}

	public void delete(Integer id) {
		this.categoriesRepository.deleteById(id);
	}

	public Categories findById(Integer id) {
		Categories categories = categoriesRepository.findByCategoriesId(id);
		return categories;
	}

	public void saveImage(MultipartFile imageFile, Categories categories) throws Exception {
		Path absolutePath = Paths.get(".");

		byte[] bytes = imageFile.getBytes();
		Path path = Paths.get(absolutePath + "/src/main/resources/static/photo/" + imageFile.getOriginalFilename());
		Files.write(path, bytes);
		categories.setThumbnail(bytes);
		this.categoriesRepository.save(categories);

	}

}
