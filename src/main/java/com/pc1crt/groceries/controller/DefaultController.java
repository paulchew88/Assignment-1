package com.pc1crt.groceries.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pc1crt.groceries.service.ProductService;
@Controller
public class DefaultController {
	@Autowired
	 private ProductService productService;
	
	@RequestMapping("/")
	public String home(Model m) {
		
		return "home";
		}
	@RequestMapping("/cart")
    public String basket(){
        return "cart";
    }
	@GetMapping("/search/product/")
	public String search(@RequestParam(value = "search", required = false)String searchTerm, Model model) {
			if(searchTerm !=null) {
			model.addAttribute("product",productService.search(searchTerm));
			}
	return"lists/ListProduct";
	}
	
}
