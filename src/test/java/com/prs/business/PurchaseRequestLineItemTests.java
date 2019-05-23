package com.prs.business;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prs.db.PurchaseRequestLineItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseRequestLineItemTests {
	
	@Autowired
	private PurchaseRequestLineItemRepository prliRepository;
	
	@Test
	public void testPurchaseRequestLineItemGetAll() {
		Iterable<PurchaseRequestLineItem> prli = prliRepository.findAll();
		assertNotNull(prli);
		
	}
	
	@Test
	public void testPurchaseRequestLineItemAddAndDelete() {
		//How do you enter test date for type PurchaseRequest and type Product?
		//PurchaseRequestLineItem prli = new PurchaseRequestLineItem("testpr", "testprod", 2);
		//save a user
		//assertNotNull(prliRepository.save(prli));
	}
}