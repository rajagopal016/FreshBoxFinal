package com.freshbox.freshbox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freshbox.freshbox.dao.CuisineDAO;
import com.freshbox.freshbox.dao.ProductDAO;
import com.freshbox.freshbox.model.Cuisine;
import com.freshbox.freshbox.model.Product;

public class CuisineController {
	
	
	@Autowired
	CuisineDAO cuisinedao;
	@RequestMapping("/addProducts") public String addProduct() { 
		return "newFoodItem"; 
	}
	@RequestMapping("/listCuisine1")
	public String listCuisines(Model model) {
		List<Cuisine> cuisine = cuisinedao.getCuisines();
		model.addAttribute("cuisines", cuisine);
		return "listcuisine";
		
	}

}
