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
import org.springframework.web.servlet.view.RedirectView;

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
public class AdminController {
	@Autowired
	UserDAO1 userdao;
	
	public boolean checkAdminLogin(String uname, String password) {
		if(uname.equals("admin") && password.equals("asd")) {
			//System.out.println("loggedin");
			return true;
		}else {
			return false;
			
		} 
	}
	
	@RequestMapping(value = "/adminlogin", method = { RequestMethod.GET, RequestMethod.POST })
	public String singleSignOn(@RequestParam(value="username") String uname,@RequestParam(value="password") String password, HttpServletRequest request, HttpServletResponse response) {
		//System.out.println(uname);
		if(checkAdminLogin(uname, password)) {
			//System.out.println("loggedin");
			response.addCookie(new Cookie("adminlogin", uname));
		}else {
			response.addCookie(new Cookie("adminlogin", "invalid"));
			
		}   
		return "adash";
	}
	
	
	@RequestMapping(value = "/adminDashboard", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminDashboard(@CookieValue(name = "adminlogin", defaultValue = "invalid") String loginCookie, HttpServletRequest request, HttpServletResponse response) {
		if(loginCookie == "admin") {
			return "admindashboard";
		}else {
			return "adminlogin";
			
		}
		
		
	}
	
	
		
}
