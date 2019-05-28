package com.prs.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.prs.business.PurchaseRequest;
import com.prs.business.PurchaseRequestLineItem;
import com.prs.db.PurchaseRequestLineItemRepository;
import com.prs.db.PurchaseRequestRepository;

@RestController
@RequestMapping("/purchase-request-line-items")
public class PurchaseRequestLineItemController {

	@Autowired
	private PurchaseRequestLineItemRepository prliRepository;
	@Autowired
	private PurchaseRequestRepository prRepository;

	@GetMapping("/")
	public JsonResponse getAll() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(prliRepository.findAll());
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<PurchaseRequestLineItem> prli = prliRepository.findById(id);
			if (prli.isPresent())
				jr = JsonResponse.getInstance(prli);
			else
				jr = JsonResponse.getInstance("No line item found for id " + id);
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PostMapping("/")
	public JsonResponse add(@RequestBody PurchaseRequestLineItem prli) {
		JsonResponse jr = null;
		// NOTE: May need to enhance exception handling if more than one exception type
		// needs to be caught
		try {
			jr = JsonResponse.getInstance(prliRepository.save(prli));
			recalculatePrTotal(prli);
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PutMapping("/")
	public JsonResponse update(@RequestBody PurchaseRequestLineItem prli) {
		JsonResponse jr = null;
		// NOTE: May need to enhance exception handling if more than one exception type
		// needs to be caught
		try {
			if (prliRepository.existsById(prli.getId())) {
				jr = JsonResponse.getInstance(prliRepository.save(prli));
				recalculatePrTotal(prli);
			} else {
				jr = JsonResponse.getInstance(
						"Line item id: " + prli.getId() + " does not exist and you are attempting to save it.");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@DeleteMapping("/")
	public JsonResponse delete(@RequestBody PurchaseRequestLineItem prli) {
		JsonResponse jr = null;
		// NOTE: May need to enhance exception handling if more than one exception type
		// needs to be caught
		try {
			if (prliRepository.existsById(prli.getId())) {
				prliRepository.delete(prli);
				jr = JsonResponse.getInstance("Line item deleted.");
				recalculatePrTotal(prli);
			} else {
				jr = JsonResponse.getInstance(
						"Line item id: " + prli.getId() + " does not exist and you are attempting to delete it.");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	private void recalculatePrTotal(PurchaseRequestLineItem prli) {
		double sum = 0.00;
		PurchaseRequest pr = prli.getPurchaseRequest();
		Iterable<PurchaseRequestLineItem> prlis = prliRepository.findByPurchaseRequest(pr);
		for (PurchaseRequestLineItem prli1 : prlis) {
			sum += prli1.getProduct().getPrice() * prli1.getQuantity();
		}
		pr.setTotal(sum);
		prRepository.save(pr);
	}
}