package com.ga.happinessadopter.model;
import javax.persistence.*;
@Entity
@Table(name="Pet")
public class Pet {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, updatable = false)
	private int id;
	@Column(name = "type", nullable = false, updatable = true)
	private String type;
	
	@Column(name = "name", nullable = false, updatable = true)
	private String name;
	
	@Column(name = "gender", nullable = false, updatable = true)
	private String gender;
	
	
	
	@Column(name = "family", nullable = true, updatable = true)
	private String family;
	
	@Column(name = "age", nullable = false, updatable = true)
	private String age;
	
	@Column(name = "petImage", nullable = false, updatable = true)
	private String petImage;
	@Column(name = "location", nullable = false, updatable = true)
	private String location;
	@Column(name = "describtion", nullable = false, updatable = true)
	private String describtion;
	
	
	@ManyToOne
	@JoinColumn(name = "FK_ownerId")
	private User owner;
	@ManyToOne
	@JoinColumn(name = "FK_adopterId")
	private User adopter;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPetImage() {
		return petImage;
	}
	public void setPetImage(String petImage) {
		this.petImage = petImage;
	}
	public String getDescribtion() {
		return describtion;
	}
	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public User getAdopter() {
		return adopter;
	}
	public void setAdopter(User adopter) {
		this.adopter = adopter;
	}
}
