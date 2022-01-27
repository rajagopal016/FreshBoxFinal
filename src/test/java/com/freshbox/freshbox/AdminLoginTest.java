package com.freshbox.freshbox;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.freshbox.freshbox.controller.AdminController;

public class AdminLoginTest {
	AdminController adminController;
	@BeforeEach                                         
    void setUp() {
		adminController = new AdminController();
    }
	
	
	@Test                                               
    @DisplayName("Right Login id and password")   
    void testMultiply() {
        assertEquals(true, adminController.checkAdminLogin("admin", "asd"),     
                "Right Login credentials should work");  
    }

}
