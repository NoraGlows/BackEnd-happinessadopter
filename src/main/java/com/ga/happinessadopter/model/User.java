package com.ga.happinessadopter.model;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


	@Entity
	@Table(name = "User")
	public class User {
		@Id
		@GeneratedValue
		@Column(name = "id", nullable = false, updatable = false)
		private int id;
		
		@Column(name="firstName", nullable = false, updatable = true)
		private String firstName;
		
		@Column(name="lastName", nullable = false, updatable = true)
		private String lastName;
		
		@Column(name="emailAddress", nullable = false, updatable = true)
		private String emailAddress;
		
		@Column(name="password", nullable = false, updatable = true)
		private String password;
		
		@Column(name="phoneNumber", nullable = false, updatable = true)
		private int phoneNumber;
		
		@Column(name="userImage", nullable = true, updatable = true)
		private String userImage;
		
		@Column(name="userRole", nullable = false, updatable = true)
		private String userRole;
//		private String oldPass;
		
		public String getUserRole() {
			return userRole;
		}

		public void setUserRole(String userRole) {
			this.userRole = userRole;
		}


		@JsonBackReference("announcement")
		@OneToMany(mappedBy="owner")
		private Set<Announcement> announcement;

		@JsonBackReference("owner")
		@OneToMany(mappedBy="owner")
		private Set<Pet> ownedPets;
		
		@JsonBackReference("adopter")
		@OneToMany(mappedBy="adopter")
		private Set<Pet> adoptedPets;
		
		public Set<Announcement> getAnnouncement() {
			return announcement;
		}

		public void setAnnouncement(Set<Announcement> announcement) {
			this.announcement = announcement;
		}

		public Set<Pet> getOwnedPets() {
			return ownedPets;
		}

		public void setOwnedPets(Set<Pet> ownedPets) {
			this.ownedPets = ownedPets;
		}

		public Set<Pet> getAdoptedPets() {
			return adoptedPets;
		}

		public void setAdoptedPets(Set<Pet> adoptedPets) {
			this.adoptedPets = adoptedPets;
		}

		
	
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmailAddress() {
			return emailAddress;
		}

		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}

		public int getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(int phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getUserImage() {
			return userImage;
		}

		public void setUserImage(String userImage) {
			this.userImage = userImage;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

//		public String getOldPass() {
//			return oldPass;
//		}
//
//		public void setOldPass(String oldPass) {
//			this.oldPass = oldPass;
//		}

		

}