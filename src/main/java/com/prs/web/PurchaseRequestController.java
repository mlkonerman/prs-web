package com.prs.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prs.business.PurchaseRequest;
import com.prs.db.PurchaseRequestRepository;

@RestController
@RequestMapping("/purchase-requests")
public class PurchaseRequestController {

	@Autowired
	private PurchaseRequestRepository prRepository;

	@GetMapping("/")
	public JsonResponse getAll() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(prRepository.findAll());
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<PurchaseRequest> pr = prRepository.findById(id);
			if (pr.isPresent())
				jr = JsonResponse.getInstance(pr);
			else
				jr = JsonResponse.getInstance("No purchase request found for id " + id);
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@PostMapping("/")
	public JsonResponse add(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		// NOTE: May need to enhance exception handling if more than one exception type
		// needs to be caught
		try {
			jr = JsonResponse.getInstance(prRepository.save(pr));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
	@PutMapping("/")
	public JsonResponse update(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		// NOTE: May need to enhance exception handling if more than one exception type
		// needs to be caught
		try {
			if (prRepository.existsById(pr.getId())) {

				jr = JsonResponse.getInstance(prRepository.save(pr));
			} else {
				jr = JsonResponse.getInstance(
						"Purchase request id: " + pr.getId() + " does not exist and" + " you are attempting to save it.");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@DeleteMapping("/")
	public JsonResponse delete(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		// NOTE: May need to enhance exception handling if more than one exception type
		// needs to be caught
		try {
			if (prRepository.existsById(pr.getId())) {
				prRepository.delete(pr);
				jr = JsonResponse.getInstance("Purchase request deleted.");
			} else {
				jr = JsonResponse.getInstance(
						"Purchase request id: " + pr.getId() + " does not exist and " + " you are attempting to delete it.");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
	
}
