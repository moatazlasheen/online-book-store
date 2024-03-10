package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "borrowing_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingRequest {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	@ManyToOne
	@JoinColumn(name="book_id", nullable=false)
	private Book book;

	private LocalDate borrowingDate;
	private LocalDate returningDate;
}
