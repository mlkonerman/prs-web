package com.prs.business;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prs.db.PurchaseRequestLineItemRepository;
import com.prs.db.PurchaseRequestRepository;
import com.prs.db.UserRepository;
import com.prs.db.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PurchaseRequestLineItemTests {

	@Autowired
	private PurchaseRequestLineItemRepository prliRepository;
	@Autowired 
	private ProductRepository productRepository;
	@Autowired
	private PurchaseRequestRepository prRepository;
	@Autowired
	private UserRepository userRepository;

	@Test
	public void testPurchaseRequestLineItemGetAll() {
		Iterable<PurchaseRequestLineItem> prli = prliRepository.findAll();
		assertNotNull(prli);

	}

	@Test
	public void testPurchaseRequestLineItemAddAndDelete() {
		// How do you enter test data for type PurchaseRequest and type Product?
		// 1) create a new PR (PR pr = new PR(...) -> you'll also need a valid user
		//    and save that pr
		// 2) get a product (using the productRepo)
		// 3) pass those into the constructor for PRLI
		Iterable<User> users = userRepository.findAll();
		User u = users.iterator().next();
		PurchaseRequest pr = new PurchaseRequest(u, "test desc", "test justification",
				LocalDateTime.of(2019, 05, 29, 8, 00), "test deliver mode", "test status", 100.00, 
				LocalDateTime.of(2019, 05, 23, 11, 00), "test rejection");
		prRepository.save(pr);		
		Iterable<Product> products = productRepository.findAll();
		Product p = products.iterator().next();
		PurchaseRequestLineItem prli = new PurchaseRequestLineItem(pr, p, 2);
		// save a prli
		assertNotNull(prliRepository.save(prli));
		// delete a prli
		prliRepository.delete(prli);
		//confirm user deletion by getting the user by id
		assertFalse(prliRepository.findById(prli.getId()).isPresent());
	}
}