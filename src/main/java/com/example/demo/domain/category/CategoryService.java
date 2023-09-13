package com.example.demo.domain.category;

import com.example.demo.core.exception.IdNotFoundResponseError;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class CategoryService {

    /**
     * Need to add:
     * - Exceptions
     */

    @Autowired
    private CategoryRepository repository;

    public List<Category> getallCategories () {
        log.info("All categories shown");
        return repository.findAll();
    }

    public Category getSingleCategory (UUID id) throws IdNotFoundResponseError{
        log.info("ID: " + id + " category");
        return repository.findById(id).orElseThrow(() -> new IdNotFoundResponseError(id.toString()));
    }

    public Category postACategory(Category category) {
        log.info("ID: " + category.getId() + " category created");
        return repository.save(category);
    }

    public Category putACategory(Category category, UUID id) throws IdNotFoundResponseError{
        log.info("ID: " + id + " category updated");
        if (repository.existsById(id)) {
            category.setId(id);
            return repository.save(category);
        }
        return repository.findById(id).orElseThrow(() -> new IdNotFoundResponseError(id.toString()));

    }

    public void deleteACategory(UUID id) {
        log.info(id + " category deleted");
        repository.deleteById(id);
    }
}