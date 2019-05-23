package com.prs.business;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prs.db.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductTests {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void testProductGetAll() {
		Iterable<Product> products = productRepository.findAll();
		assertNotNull(products);
		
	}
}