package com.example.demo.domain.category;

import com.example.demo.core.exception.IdNotFoundResponseError;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> getallCategories () {
        List<Category> categories = repository.findAll();
        log.info("All categories shown");
        return categories;
    }

    public Category getSingleCategory (UUID id) throws IdNotFoundResponseError{
        Category category = repository.findById(id).orElseThrow(() -> new IdNotFoundResponseError(id.toString()));
        log.info("ID: " + id + " category");
        return category;
    }

    public Category postACategory(Category category) {
        Category newCategory = repository.save(category);
        log.info("ID: " + category.getId() + " category created");
        return newCategory;
    }

    public Category putACategory(Category category, UUID id) throws IdNotFoundResponseError{
        if (repository.existsById(id)) {
            category.setId(id);
            return repository.save(category);
        }
        Category modifiedCategory = repository.findById(id).orElseThrow(() -> new IdNotFoundResponseError(id.toString()));
        log.info("ID: " + id + " category updated");
        return modifiedCategory;

    }

    public void deleteACategory(UUID id) {
        repository.deleteById(id);
        log.info(id + " category deleted");
    }
}
