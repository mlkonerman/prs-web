package com.prs.business;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prs.db.PurchaseRequestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PurchaseRequestTests {

	@Autowired
	private PurchaseRequestRepository prRepository;

	@Test
	public void testPurchaseRequestGetAll() {
		Iterable<PurchaseRequest> purchaseReqs = prRepository.findAll();
		assertNotNull(purchaseReqs);

	}
}