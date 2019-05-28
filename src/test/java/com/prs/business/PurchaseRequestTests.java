package com.prs.business;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prs.db.PurchaseRequestRepository;
import com.prs.db.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PurchaseRequestTests {

	@Autowired
	private PurchaseRequestRepository prRepository;
	@Autowired
	private UserRepository userRepository;

	@Test
	public void testPurchaseRequestGetAll() {
		Iterable<PurchaseRequest> purchaseReqs = prRepository.findAll();
		assertNotNull(purchaseReqs);

	}

	@Test
	public void testPurchaseRequestAddAndDelete() {
		Iterable<User> users = userRepository.findAll();
		User u = users.iterator().next();

		PurchaseRequest pr = new PurchaseRequest(u, "test description", "test justification",
				LocalDateTime.of(2019, 05, 29, 8, 00), "test delivery mode", "test status", 100.00,
				LocalDateTime.of(2019, 05, 23, 11, 00), "test rejection");
		// save a purchase request
		assertNotNull(prRepository.save(pr));
		prRepository.delete(pr);
		// confirm user deletion by getting the user by id
		assertFalse(prRepository.findById(pr.getId()).isPresent());
	}
}