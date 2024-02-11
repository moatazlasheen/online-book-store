package com.example.demo.repos;

import com.example.demo.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Integer> {

    @Query("select c from Category c where lower(c.name) = ?1")
    Category findCategoryByNameIgnoringCase(String name);
}
