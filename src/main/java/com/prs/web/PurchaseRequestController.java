package com.prs.web;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.prs.business.PurchaseRequest;
import com.prs.business.User;
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

//	@PostMapping("/")
//	//won't need this because of submitNew method
//	public JsonResponse add(@RequestBody PurchaseRequest pr) {
//		JsonResponse jr = null;
//		// NOTE: May need to enhance exception handling if more than one exception type
//		// needs to be caught
//		try {
//			jr = JsonResponse.getInstance(prRepository.save(pr));
//		} catch (Exception e) {
//			jr = JsonResponse.getInstance(e);
//		}
//		return jr;
//	}

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
						"Purchase request id: " + pr.getId() + " does not exist and you are attempting to save it.");
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
						"Purchase request id: " + pr.getId() + " does not exist and you are attempting to delete it.");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PostMapping("/submit-new")
	public JsonResponse submitNew(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		try {
			pr.setStatus("New");
			pr.setSubmittedDate(LocalDateTime.now());
			jr = JsonResponse.getInstance(prRepository.save(pr));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PutMapping("/submit-review")
	public JsonResponse submitForReview(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		try {
			if (pr.getTotal() <= 50) {
				pr.setStatus("Approved");
			} else {
				pr.setStatus("Review");
			}
			pr.setSubmittedDate(LocalDateTime.now());
			jr = JsonResponse.getInstance(prRepository.save(pr));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@GetMapping("/list-review")
	public JsonResponse listAllInReviewStatusAndNotReviewer(@RequestBody User u) {
		JsonResponse jr = null;
		try {
			Iterable<PurchaseRequest> pr = prRepository.findByStatusAndUserNot("Review", u);
			jr = JsonResponse.getInstance(pr);
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PutMapping("/approve")
	public JsonResponse approvePR(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		try {
			pr.setStatus("Approved");
			jr = JsonResponse.getInstance(prRepository.save(pr));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PutMapping("/reject")
	public JsonResponse rejectPR(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		try {
			pr.setStatus("Rejected");
			jr = JsonResponse.getInstance(prRepository.save(pr));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}
}
