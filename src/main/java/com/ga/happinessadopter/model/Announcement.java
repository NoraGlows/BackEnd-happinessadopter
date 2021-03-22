package com.ga.happinessadopter.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name="Announcement")
public class Announcement {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, updatable = false)
	private int id;
	@Column(name="annouTitle", nullable = false, updatable = true)
	private String annouTitle;
	
	@Column(name="annouPictrue", nullable = false, updatable = true)
    private String annouPictrue;
	
	@Column(name="annoulocation", nullable = false, updatable = true)
	private String annoulocation;
	
	@Column(name="annouDescription", nullable = false, updatable = true)
	private String annouDescription;
	
	@Column(name="phoneNumber", nullable = false, updatable = true ,length = 10)
	private int phoneNumber;
	
	@ManyToOne
	@JoinColumn(name = "FK_ownerId")
	private User owner;
	
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getAnnouPictrue() {
		return annouPictrue;
	}
	public void setAnnouPictrue(String annouPictrue) {
		this.annouPictrue = annouPictrue;
	}
	public String getAnnoulocation() {
		return annoulocation;
	}
	public void setAnnoulocation(String annoulocation) {
		this.annoulocation = annoulocation;
	}
	public String getAnnouDescription() {
		return annouDescription;
	}
	public void setAnnouDescription(String annouDescription) {
		this.annouDescription = annouDescription;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAnnouTitle() {
		return annouTitle;
	}
	public void setAnnouTitle(String annouTitle) {
		this.annouTitle = annouTitle;
	}
	
}
