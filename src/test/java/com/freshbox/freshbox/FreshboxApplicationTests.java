package com.freshbox.freshbox;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.freshbox.freshbox.controller.AdminController;
import com.freshbox.freshbox.controller.EProductController;
import com.freshbox.freshbox.controller.PurchaseController;

@SpringBootTest
class FreshboxApplicationTests {

	AdminController adminController;
	PurchaseController purchaseController;
	@BeforeEach                                         
    void setUp() {
		adminController = new AdminController();
    }
	
	
	@Test                                               
    @DisplayName("Right Login id and password")   
    void testRightIdPass() {
        assertEquals(true, adminController.checkAdminLogin("admin", "asd"),     
                "Right Login credentials should work");  
    }
	
	
	@Test                                               
    @DisplayName("ID wrong casing")   
    void testWrongCasingId() {
        assertEquals(false, adminController.checkAdminLogin("Admin", "asd"),     
                "Wrong ID case should not work");  
    }
	
	@Test                                               
    @DisplayName("Password wrong casing")   
    void testWrongCasingPassword() {
        assertEquals(false, adminController.checkAdminLogin("admin", "Asd"),     
                "Wrong Password case should not work");  
    }
	
	@Test                                               
    @DisplayName("Wrong Password")   
    void testWrongPassword() {
        assertEquals(false, adminController.checkAdminLogin("admin", "fdsf"),     
                "Wrong Password should not work");  
    }
	
	@Test                                               
    @DisplayName("Wrong Id")   
    void testWrongId() {
        assertEquals(false, adminController.checkAdminLogin("fsdf", "asd"),     
                "Wrong Id should not work");  
    }
	
	@Test                                               
    @DisplayName("Normal inputs")
	void normalInputs(int price, int discount){
		
		assertEquals(90, purchaseController.priceAfterDiscount(100, 10),     
                "Normal inputs should work"); 
	}
	
	
}
