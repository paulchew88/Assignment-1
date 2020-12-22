package com.pc1crt.groceries.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pc1crt.groceries.error.UnavailableException;
import com.pc1crt.groceries.model.Categories;
import com.pc1crt.groceries.repository.CategoriesRepository;
import com.pc1crt.groceries.service.CategoriesService;
import com.sun.tools.sjavac.Log;

@Controller
public class CategoriesController {
	@Autowired
	CategoriesService categoriesService;

	@GetMapping("/add/categories")
	public String newForm(Model model) {
		model.addAttribute("categories", new Categories());
		return "/newForms/NewCategoriesForm";
	}

	@RequestMapping(value = "/new/categories", method = RequestMethod.POST)
	public String submitCategory(@Valid @ModelAttribute("categories") Categories categories, BindingResult result,
			ModelMap model) throws Exception {

		categoriesService.save(categories);

		return "redirect:/list/categories";

	}

	@RequestMapping("/list/categories")
	public String findAll(Model model) {
		model.addAttribute("categories", categoriesService.getAllCategories());
		return "/lists/ListCategories";
	}

	@RequestMapping("/remove/categories/{id}")
	public String delete(@PathVariable Integer id) {
		try {

			categoriesService.delete(categoriesService.findById(id).getCategoriesId());
		} catch (Exception e) {
			throw new UnavailableException("Could not delete "+ categoriesService.findById(id).getName() );
		}
		return "redirect:/list/categories";
	}

	@RequestMapping("edit/categories/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Categories categories = categoriesService.findById(id);
		model.addAttribute(categories);

		return "/updateForms/editCategories";
	}

	@PostMapping("edit/categories/{id}")
	public String update(@PathVariable Integer id, @Valid Categories categories, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "/updateForms/editCategories";
		} else {
			Categories category = categoriesService.findById(id);
			category.setDescription(categories.getDescription());
			category.setName(categories.getName());
			category.setThumbnail(categories.getThumbnail());
			categoriesService.save(category);
			return "redirect:/list/categories";
		}

	}

	@ExceptionHandler({ UnavailableException.class })
	public String getUnavailable(UnavailableException ex, Model model) {
		model.addAttribute("error", ex.getMessage());
		return "home";

	}

}
