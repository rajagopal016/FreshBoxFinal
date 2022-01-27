package com.freshbox.freshbox.controller;


	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.freshbox.freshbox.dao.UserDAO1;
import com.freshbox.freshbox.model.AuthenticationRequest;
import com.freshbox.freshbox.model.AuthenticationResponse;
import com.freshbox.freshbox.model.User;


import com.freshbox.freshbox.dao.UserRepository;
import com.freshbox.freshbox.dao.UserRepository2;


@Controller
@Component
public class LoginController {
	@Autowired
	UserDAO1 userdao;
	@RequestMapping(value = "/loginaction", method = RequestMethod.POST)
	public void singleSignOn(@RequestParam(value="email") String uname,@RequestParam(value="password") String password, HttpServletRequest request, HttpServletResponse response) {
		User user = userdao.SELECT_USER_FOR_LOGIN(uname, password);
		if(user != null) {
			response.addCookie(new Cookie("login", uname));
		}else {
			response.addCookie(new Cookie("login", "invalid"));
			
		}
	    
	    try {
			response.sendRedirect("/home");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@RequestMapping(value = "/userlogoff", method = RequestMethod.GET)
	public void logOff(HttpServletRequest request, HttpServletResponse response) {
		
		response.addCookie(new Cookie("login", "invalid"));
		
	    
	    try {
			response.sendRedirect("/home");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "/userreg", method = RequestMethod.POST)
	public String register(@RequestParam(value="email") String email, @RequestParam(value="address") String address, @RequestParam(value="mobile") String mobile, @RequestParam(value="age") int age, @RequestParam(value="fullname") String fullname, @RequestParam(value="uname") String uname,@RequestParam(value="psw") String password, HttpServletRequest request, HttpServletResponse response) {
		User user = new User(uname, fullname, mobile, address, age, password, email);
		System.out.println(user);
		try {
			
			String sage = String.valueOf(age);
			Exception e = userdao.INSERT_USER(uname, password, fullname, sage, mobile, address, email);
			if( e != null) {
				response.addCookie(new Cookie("login", "invalid"));
				System.out.println(e);
				return "userregfail";
				
			}else {
				return "userreg";
			}
			
			
		}
		catch(Exception e){
			
			System.out.println(e);
			return "userregfail";
		}
		

	}
	
	@RequestMapping(value = "/myprofile", method = RequestMethod.GET)
	public String register(@CookieValue(name = "login", defaultValue = "invalid") String loginCookie, Model model) {
		User user = userdao.getUser(loginCookie);
		model.addAttribute("userdetail", user);		
		return "myprofile";
		
	}
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String changepassword(@CookieValue(name = "login", defaultValue = "invalid") String loginCookie, Model model) {
			
		return "changepassword";
		
	}
	@RequestMapping(value = "/saveNewPassword", method = RequestMethod.POST)
	public String savepassword(@CookieValue(name = "login", defaultValue = "invalid") String loginCookie, @RequestParam(value="password") String newpass, Model model) {
		User user = userdao.getUser(loginCookie);
		System.out.println(user);
		user.setUser_Password(newpass);
		
		try {
			userRepository.save(user);
//			Exception e = userdao.UPDATE_PASSWORD(loginCookie, newpass);
//			if( e != null) {
//				System.out.println(e);
//				return "userregfail";
//				
//			}else {
//				return "userreg";
//			}
			return "userreg";
			
		}
		catch(Exception e){
			
			System.out.println(e);
			return "userregfail";
		}


		
	}
	
}
