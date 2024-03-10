package com.example.demo.repos;

import com.example.demo.entities.Book;
import com.example.demo.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends CrudRepository<Book, Integer> {
    Iterable<Book> findByAvailable(boolean available);

    Iterable<Book> findByCategory(Category category);
}
