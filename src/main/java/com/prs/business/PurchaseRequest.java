package com.prs.business;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PurchaseRequest {
	
	@Id //declares id the primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int id;
	@ManyToOne
	@JoinColumn(name = "userID")
	private User user;
	private String description;
	private String justification;
	private LocalDateTime dateNeeded;
	private String deliveryMode;
	private String status;
	private double total;
	private LocalDateTime submittedDate;
	private String reasonForRejection;
	
	public PurchaseRequest(int id, User user, String description, String justification, LocalDateTime dateNeeded,
			String deliveryMode, String status, double total, LocalDateTime submittedDate,
			String reasonForRejection) {
	super();
	this.id = id;
	this.user = user;
	this.description = description;
	this.justification = justification;
	this.dateNeeded = dateNeeded;
	this.deliveryMode = deliveryMode;
	this.status = status;
	this.total = total;
	this.submittedDate = submittedDate;
	this.reasonForRejection = reasonForRejection;
	
}

public PurchaseRequest() {
	super();	
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getJustification() {
	return justification;
}

public void setJustification(String justification) {
	this.justification = justification;
}

public LocalDateTime getDateNeeded() {
	return dateNeeded;
}

public void setDateNeeded(LocalDateTime dateNeeded) {
	this.dateNeeded = dateNeeded;
}

public String getDeliveryMode() {
	return deliveryMode;
}

public void setDeliveryMode(String deliveryMode) {
	this.deliveryMode = deliveryMode;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public double getTotal() {
	return total;
}

public void setTotal(double total) {
	this.total = total;
}

public LocalDateTime getSubmittedDate() {
	return submittedDate;
}

public void setSubmittedDate(LocalDateTime submittedDate) {
	this.submittedDate = submittedDate;
}

public String getReasonForRejection() {
	return reasonForRejection;
}

public void setReasonForRejection(String reasonForRejection) {
	this.reasonForRejection = reasonForRejection;
}

@Override
public String toString() {
	return "Purchase Request [id=" + id + ", User=" + user + ", Description=" + description + 
			", Justification=" + justification + ", DateNeeded=" + dateNeeded + 
			", DeliveryMode=" + deliveryMode +", Status=" + status +", Total=" + total +
			", DateSubmitted=" + submittedDate + ", ReasonForRejection=" + reasonForRejection +"]";
	
}

}
