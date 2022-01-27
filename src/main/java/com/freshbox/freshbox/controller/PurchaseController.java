package com.freshbox.freshbox.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.freshbox.freshbox.dao.CuisineRepository;
import com.freshbox.freshbox.dao.ProductDAO;
import com.freshbox.freshbox.dao.ProductRepository;
import com.freshbox.freshbox.dao.UserDAO1;
import com.freshbox.freshbox.model.Cuisine;
import com.freshbox.freshbox.model.Product;
import com.freshbox.freshbox.model.User;
import com.freshbox.freshbox.util.*;

@Controller
public class PurchaseController {
	@Autowired
	ProductDAO productdao;
	@Autowired
	UserDAO1 userdao;
	@Autowired
	CuisineRepository cuisineRepository;
	@Autowired
	ProductRepository productRepository;
	@RequestMapping("/sideBarFilter/{cuisinename}")
	public String addProduct(@PathVariable("cuisinename") String cuisinename, Model model) {
		
		List<Product> products = productdao.listProductByCuisine(cuisinename);
		List<Cuisine> cuisines = productdao.getCuisines();
		model.addAttribute("products", products);
		model.addAttribute("cuisines", cuisines);
		return "index";
		
	}
	public int positionInArray(String[] array, String element) {
		int position = -1;
		for(int i = 0; i < array.length; i++) {
			if(array[i].equals(element)) {
				position = i;
				break;
			}
		}
		return position;
		
	}
	@RequestMapping("/addToCart")
	public RedirectView addToCart(@RequestParam("prod_id") String prod_id, @CookieValue(name = "login", defaultValue = "invalid") String loginCookie, Model model) {
		if(loginCookie.equals(null)||loginCookie.equals("invalid")) {
			return new RedirectView("userlogin");
		}
		User user = userdao.getUser(loginCookie);
		
		String cart = user.getUser_Cart();
		String qty = user.getUser_Qty();
		String[] cart_array = cart.split(",");
		String[] cart_qty = qty.split(",");
		
		if(positionInArray(cart_array, prod_id) != -1) {
			System.out.println("Entered 1");
			int position = positionInArray(cart_array, prod_id);
			int int_qty = Integer.parseInt(cart_qty[position]);
			
			int_qty = int_qty + 1;
			
			cart_qty[position] = String.valueOf(int_qty);
		
			String qty_updated = "";
			String cart_updated = cart;
			for(int i = 0; i < cart_array.length; i++) {
				if(i == 0) {
					qty_updated = qty_updated.concat(cart_qty[i]);
				}else {
					qty_updated = qty_updated.concat(",").concat(cart_qty[i]);
				}
				
			}
			
			userdao.UPDATE_CART(loginCookie, cart_updated, qty_updated);
			
		}else {
			System.out.println("Entered -1");
			if(cart.length() == 0) {
				String cart_updated = cart.concat(prod_id);
				String qty_updated = qty.concat("1");
				userdao.UPDATE_CART(loginCookie, cart_updated, qty_updated);
			}else {
				String cart_updated = cart.concat(",").concat(prod_id);
				String qty_updated = qty.concat(",").concat("1");
				userdao.UPDATE_CART(loginCookie, cart_updated, qty_updated);
			}
			
			
			
		}
		
		return new RedirectView("home");
		
	}
	
	@RequestMapping("/addToCart1")
	public RedirectView addToCart1(@RequestParam("prod_id") String prod_id, @CookieValue(name = "login", defaultValue = "invalid") String loginCookie, Model model) {
		User user = userdao.getUser(loginCookie);
		
		String cart = user.getUser_Cart();
		String qty = user.getUser_Qty();
		String[] cart_array = cart.split(",");
		String[] cart_qty = qty.split(",");
		
		if(positionInArray(cart_array, prod_id) != -1) {
			System.out.println("Entered 1");
			int position = positionInArray(cart_array, prod_id);
			int int_qty = Integer.parseInt(cart_qty[position]);
			
			int_qty = int_qty + 1;
			
			cart_qty[position] = String.valueOf(int_qty);
		
			String qty_updated = "";
			String cart_updated = cart;
			for(int i = 0; i < cart_array.length; i++) {
				if(i == 0) {
					qty_updated = qty_updated.concat(cart_qty[i]);
				}else {
					qty_updated = qty_updated.concat(",").concat(cart_qty[i]);
				}
				
			}
			
			userdao.UPDATE_CART(loginCookie, cart_updated, qty_updated);
			
		}else {
			System.out.println("Entered -1");
			String cart_updated = cart.concat(",").concat(prod_id);
			String qty_updated = qty.concat(",").concat("1");
			
			userdao.UPDATE_CART(loginCookie, cart_updated, qty_updated);
		}
		
		return new RedirectView("cart");
		
	}
	
	@RequestMapping("/removeFromCart")
	public RedirectView removeFromCart(@RequestParam("prod_id") String prod_id, @CookieValue(name = "login", defaultValue = "invalid") String loginCookie, Model model) {
		User user = userdao.getUser(loginCookie);
		
		String cart = user.getUser_Cart();
		String qty = user.getUser_Qty();
		String[] cart_array = cart.split(",");
		String[] qty_array = qty.split(",");
		
		if(qty_array[positionInArray(cart_array, prod_id)].equals("1")) {
			System.out.println("Entered 1");
			String cart_updated = "";
			String qty_updated = "";
			int pos = positionInArray(cart_array, prod_id);
			for(int i = 0; i < cart_array.length; i++) {
				System.out.println(pos);
				if( i != pos) {
					if(i > 1) {
						cart_updated = cart_updated.concat(",").concat(cart_array[i]);
						System.out.println(cart_updated);
						qty_updated = qty_updated.concat(",").concat(qty_array[i]);
					}else {
						cart_updated = cart_updated.concat(cart_array[i]);
						qty_updated = qty_updated.concat(qty_array[i]);
					}
				}else {
					
				}
			}
			
			userdao.UPDATE_CART(loginCookie, cart_updated, qty_updated);
			
		}else {
			System.out.println("Entered -1");
			String cart_updated = "";
			String qty_updated = "";
			int pos = positionInArray(cart_array, prod_id);
			if(Integer.parseInt(qty_array[pos]) < 1) {
				qty_array[pos] = String.valueOf(Integer.parseInt(qty_array[pos]) - 0);
			}else {
				qty_array[pos] = String.valueOf(Integer.parseInt(qty_array[pos]) - 1);
			}
			
			for(int i = 0; i < cart_array.length; i++) {
				if(i != 0) {
					cart_updated = cart_updated.concat(",").concat(cart_array[i]);
					qty_updated = qty_updated.concat(",").concat(qty_array[i]);
				}else {
					cart_updated = cart_updated.concat(cart_array[i]);
					qty_updated = qty_updated.concat(qty_array[i]);
				}
			}
			userdao.UPDATE_CART(loginCookie, cart_updated, qty_updated);
		}
		
		return new RedirectView("cart");
		
	}
	
	public int priceAfterDiscount(int price, int discount) {
		float a = (float)discount/price;
		a = a * 100;
		
		float afterDiscount = price - a;
		
		int finalprice = Math.round(afterDiscount);
		return finalprice;
	}
	@RequestMapping("/cart")
	public String viewCart(@CookieValue(name = "login", defaultValue = "invalid") String loginCookie, Model model) {
		User user = userdao.getUser(loginCookie);
		String itemss = user.getUser_Cart();
		System.out.println(itemss);
		if(itemss.equals("")) {
			System.out.println(itemss);
		}else {
		String[] items = user.getUser_Cart().split(",");
		String[] quantity = user.getUser_Qty().split(",");
		
		List<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();

		for (int item = 0 ; item < items.length; item = item+1){
			
			Optional<Product> p = productRepository.findById(Integer.parseInt(items[item]));
			Product product = p.get();
			ArrayList<String> itemslist = new ArrayList<String>();
			itemslist.add(String.valueOf(product.getProduct_Id()));
			itemslist.add(product.getProduct_Name());
			itemslist.add(String.valueOf(product.getProduct_Price()));
			itemslist.add(String.valueOf(product.getProduct_Offers()));
			itemslist.add(String.valueOf(priceAfterDiscount(product.getProduct_Price(), product.getProduct_Offers())));
			itemslist.add(String.valueOf(quantity[item]));
			itemslist.add(String.valueOf(priceAfterDiscount(product.getProduct_Price(), product.getProduct_Offers()) * Integer.parseInt(quantity[item])));
			listOfLists.add(itemslist);
			//listOfId.add(product.getProduct_Id());
		}
		model.addAttribute("itemlist", listOfLists);
		
	}
		return "cartpage";	
	}
	
	@RequestMapping("/proceedToPurchase")
	public String proceedToPurchase(@CookieValue(name = "login", defaultValue = "invalid") String loginCookie, Model model) {
		User user = userdao.getUser(loginCookie);
		String itemss = user.getUser_Cart();
		System.out.println(itemss);
		if(itemss.equals("")) {
			System.out.println(itemss);
		}else {
		String[] items = user.getUser_Cart().split(",");
		String[] quantity = user.getUser_Qty().split(",");
		
		List<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();
		int totalAmount = 0;
		for (int item = 0 ; item < items.length; item = item+1){
			
			Optional<Product> p = productRepository.findById(Integer.parseInt(items[item]));
			Product product = p.get();
			ArrayList<String> itemslist = new ArrayList<String>();
			itemslist.add(String.valueOf(product.getProduct_Id()));
			itemslist.add(product.getProduct_Name());
			itemslist.add(String.valueOf(product.getProduct_Price()));
			itemslist.add(String.valueOf(product.getProduct_Offers()));
			itemslist.add(String.valueOf(priceAfterDiscount(product.getProduct_Price(), product.getProduct_Offers())));
			itemslist.add(String.valueOf(quantity[item]));
			itemslist.add(String.valueOf(priceAfterDiscount(product.getProduct_Price(), product.getProduct_Offers()) * Integer.parseInt(quantity[item])));
			totalAmount = totalAmount + priceAfterDiscount(product.getProduct_Price(), product.getProduct_Offers()) * Integer.parseInt(quantity[item]);
			listOfLists.add(itemslist);
			//listOfId.add(product.getProduct_Id());
		}
		model.addAttribute("itemlist", listOfLists);
		model.addAttribute("totalAmount", totalAmount);
		
	}
		return "purchasesummary";
	}
	
	
	@RequestMapping("/proceedToPayment")
	public String proceedToPayment(@RequestParam("total") String total, @CookieValue(name = "login", defaultValue = "invalid") String loginCookie, Model model) {
		User user = userdao.getUser(loginCookie);
		String itemss = user.getUser_Cart();
		return "paymentpage";
	}
	
	
	@RequestMapping("/orderSummary")
	public String orderSummary(@RequestParam("total") String total, @CookieValue(name = "login", defaultValue = "invalid") String loginCookie, Model model) {
		User user = userdao.getUser(loginCookie);
		String itemss = user.getUser_Cart();
		System.out.println(itemss);
		if(itemss.equals("")) {
			System.out.println(itemss);
		}else {
		String[] items = user.getUser_Cart().split(",");
		String[] quantity = user.getUser_Qty().split(",");
		
		List<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();
		int totalAmount = 0;
		for (int item = 0 ; item < items.length; item = item+1){
			
			Optional<Product> p = productRepository.findById(Integer.parseInt(items[item]));
			Product product = p.get();
			ArrayList<String> itemslist = new ArrayList<String>();
			itemslist.add(String.valueOf(product.getProduct_Id()));
			itemslist.add(product.getProduct_Name());
			itemslist.add(String.valueOf(product.getProduct_Price()));
			itemslist.add(String.valueOf(product.getProduct_Offers()));
			itemslist.add(String.valueOf(priceAfterDiscount(product.getProduct_Price(), product.getProduct_Offers())));
			itemslist.add(String.valueOf(quantity[item]));
			itemslist.add(String.valueOf(priceAfterDiscount(product.getProduct_Price(), product.getProduct_Offers()) * Integer.parseInt(quantity[item])));
			totalAmount = totalAmount + priceAfterDiscount(product.getProduct_Price(), product.getProduct_Offers()) * Integer.parseInt(quantity[item]);
			listOfLists.add(itemslist);
			//listOfId.add(product.getProduct_Id());
		}
		model.addAttribute("itemlist", listOfLists);
		model.addAttribute("totalAmount", totalAmount);
		
	}
		return "orderSummary";
		
	}
	
	}

