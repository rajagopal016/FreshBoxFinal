package com.freshbox.freshbox.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.freshbox.freshbox.dao.CuisineRepository;
import com.freshbox.freshbox.dao.ProductDAO;
import com.freshbox.freshbox.dao.ProductRepository;
import com.freshbox.freshbox.model.Cuisine;
import com.freshbox.freshbox.model.Product;


@Controller
public class EProductController {
	
	@Autowired
	ProductDAO productdao;
	@RequestMapping("/addProducts")
	public String addProduct(Model model) { 
		List<Cuisine> cuisine = productdao.getCuisines();
		model.addAttribute("cuisines", cuisine);
		return "newFood"; 
	}
	@RequestMapping("/listProducts")
	public String listProducts(Model model) {
		List<Product> products = productdao.getProducts();
		model.addAttribute("products", products);
		return "listFood";
	}
	
	@RequestMapping("/listCuisine")
	public String listCuisines(Model model) {
		List<Cuisine> cuisine = productdao.getCuisines();
		model.addAttribute("cuisines", cuisine);
		return "listcuisine";
		
	}
	
	@RequestMapping("/addCuisines")
	public String addCuisine(Model model) { 
		return "newcuisine"; 
	}
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping("/saveProducts")
	public String saveProduct(@RequestParam(value="name") String name,@RequestParam(value="category") String category, @RequestParam(value="price") int price,@RequestParam(value="type") String type,@RequestParam(value="cuisine") String cuisine, @RequestParam(value="description") String description, @RequestParam(value="offerpercent") int offerpercent, @RequestParam(value="available") String available, Model model) {
		//System.out.print(email);
		Product p = new Product(name, price, category, type, available, cuisine, description, offerpercent);
		try{
			productRepository.save(p);
			return "Saved";
		}catch (Exception e) {
			
			return "SaveFailed";
		}
		
	}
	
	@Autowired
	private CuisineRepository cuisinerepository;
	@PostMapping("/saveCuisines")
	public String saveProduct(@RequestParam(value="name") String name, Model model) {
		//System.out.print(email);
		Cuisine c = new Cuisine(name);
		try{
			cuisinerepository.save(c);
			return "cuisaved";
		}catch (Exception e) {
			
			return "cuisavefailed";
		}
		
	}
	

	@Autowired
	private ProductRepository productRepository2;
	@PostMapping("/editProduct")
	public String editProduct(@RequestParam(value="edit") String id, Model model) {
		int productid = Integer.parseInt(id);
		Optional<Product> p = Optional.ofNullable(new Product());
		
		p = productRepository2.findById(productid);
		Product product = p.get();
		List<Cuisine> cuisine = productdao.getCuisines();
		model.addAttribute("cuisines", cuisine);
		model.addAttribute("product", product);
		return "editproduct"; 
	}
	
	@PostMapping("/saveEditedProducts")
	public String saveEditedProduct(@RequestParam(value="id") int id, @RequestParam(value="name") String name,@RequestParam(value="category") String category, @RequestParam(value="price") int price,@RequestParam(value="type") String type,@RequestParam(value="cuisine") String cuisine, @RequestParam(value="description") String description, @RequestParam(value="offerpercent") int offerpercent, @RequestParam(value="available") String available, Model model) {
		//System.out.print(email);
		Product p = new Product(id , name, price, category, type, available, cuisine, description, offerpercent);
		try{
			productRepository.save(p);
			return "Saved";
		}catch (Exception e) {
			
			return "SaveFailed";
		}
		
	}
	
	@PostMapping("/deleteProducts")
	public String deleteProduct(@RequestParam(value="delete") int id, Model model) {
		//System.out.print(email);
		//Product p = new Product(id , name, price, category, type, available, cuisine, description, offerpercent);
		try{
			productRepository.deleteById(id);
			return "deleteproduct";
		}catch (Exception e) {
			
			return "deleteproductfail";
		}
		
	}
	
	@PostMapping("/deleteCuisines")
	public String deleteCuisine(@RequestParam(value="delete") int id, Model model) {
		//System.out.print(email);
		//Product p = new Product(id , name, price, category, type, available, cuisine, description, offerpercent);
		try{
			cuisinerepository.deleteById(id);
			return "deletecuisine";
		}catch (Exception e) {
			
			return "deletecuisinefail";
		}
		
	}
	 
	

}
