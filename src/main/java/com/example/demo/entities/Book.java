package com.example.demo.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	private String name;

	@OneToOne
	private Category category;

	private boolean available;
	
	@OneToMany(mappedBy="book")
	private Set<BorrowingRequest> borrowingRequests;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Set<BorrowingRequest> getBorrowingRequests() {
		return borrowingRequests;
	}

	public void setBorrowingRequests(Set<BorrowingRequest> borrowingRequests) {
		this.borrowingRequests = borrowingRequests;
	}
}
