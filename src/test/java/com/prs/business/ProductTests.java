package com.prs.business;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prs.db.ProductRepository;
import com.prs.db.VendorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductTests {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private VendorRepository vendorRepository;
	
	@Test
	public void testProductGetAll() {
		Iterable<Product> products = productRepository.findAll();
		assertNotNull(products);
		
	}
	
	@Test
	public void testProductAddAndDelete() {
		Iterable<Vendor> vendors = vendorRepository.findAll();
		Vendor v = vendors.iterator().next();
		Product p = new Product(v, "TEST-123", "Test product", 10.00, null, null);
		//save a user
		assertNotNull(productRepository.save(p));
		// assert that product name is correct
		assertEquals("TEST-123", p.getPartNumber());
		//delete the user
		productRepository.delete(p);
		//confirm user deletion by getting the user by id
		assertFalse(productRepository.findById(p.getId()).isPresent());
	}
}