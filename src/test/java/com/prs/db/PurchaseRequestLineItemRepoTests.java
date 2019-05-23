package com.prs.db;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.prs.business.PurchaseRequestLineItem;
import com.prs.business.User;
import com.prs.db.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class PurchaseRequestLineItemRepoTests {

	@Autowired
	private PurchaseRequestLineItemRepository prliRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void purchaseReqIdAndProductIdCombinationShouldBeUnique() {
		//
	}
	
}