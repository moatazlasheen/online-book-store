package com.example.demo.repos;

import com.example.demo.entities.BorrowingRequest;
import com.example.demo.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRequestRepo extends CrudRepository<BorrowingRequest, Integer> {

}
