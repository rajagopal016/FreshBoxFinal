package com.freshbox.freshbox.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freshbox.freshbox.dao.ProductDAO;
import com.freshbox.freshbox.dao.UserDAO1;
import com.freshbox.freshbox.dao.UserRepository;
import com.freshbox.freshbox.model.Cuisine;
import com.freshbox.freshbox.model.Product;
import com.freshbox.freshbox.model.User;
@Controller
public class IndexController {
	
	@Autowired
	ProductDAO productdao;
	@Autowired
	UserDAO1 userdao;
	
	@RequestMapping(value={"/home","/", "/index"})
	public String home(@CookieValue(name = "login", defaultValue = "invalid") String loginCookie, Model model) {
		
		if(loginCookie.equals("invalid")) {
			String User_Name = "invalid";
			Boolean loginState = false;
			int cartsize = 0;
			model.addAttribute("userdetail", User_Name);
			model.addAttribute("loginstate", loginState);
			model.addAttribute("cartsize", cartsize);
		}else {
			User user = userdao.getUser(loginCookie);
			String User_Name = user.getUser_Name();
			Boolean loginState = true;
			model.addAttribute("userdetail", User_Name);
			model.addAttribute("loginstate", loginState);
			if(user.getUser_Cart().equals("")) {
				int cartsize = 0;
				model.addAttribute("cartsize", cartsize);
			}
			else {
				int cartsize = user.getUser_Cart().split(",").length;
				model.addAttribute("cartsize", cartsize);
			}
			
		}
		
		
		List<Cuisine> cuisine = productdao.getCuisines();
		model.addAttribute("cuisines", cuisine);
		System.out.println(loginCookie);
		List<Product> products = productdao.getProducts();
		model.addAttribute("products", products);
		return "index";
	}
	
	@GetMapping("/userlogin")
	public String userlogin(Model model) {
		return "userlogin2";
	}
	
	@GetMapping("/userreg")
	public String userreg(Model model) {
		return "userregistration";
	}
	
	@GetMapping("/admin")
	public String adminlogin(Model model) {
		return "adminlogin";
	}

	
	
	
}
