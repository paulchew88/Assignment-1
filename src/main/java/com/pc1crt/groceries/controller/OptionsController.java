package com.pc1crt.groceries.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pc1crt.groceries.model.Options;
import com.pc1crt.groceries.repository.OptionsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller
public class OptionsController {
	@Autowired
	OptionsRepository optionsRepository;
	
	@RequestMapping("/add")
	public String addOption() {
		Options options = new Options("test");
		optionsRepository.save(options);
		
		return "home";
		
	}

}
