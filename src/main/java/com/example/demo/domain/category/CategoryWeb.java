package com.example.demo.domain.category;

import com.example.demo.core.exception.IdNotFoundResponseError;
import com.example.demo.domain.category.dto.CategoryDTO;
import com.example.demo.domain.category.dto.CategoryMapper;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
@RequestMapping("/blogs/categories")
@CrossOrigin(origins = "http://localhost:3000/")
public class CategoryWeb {

    @Autowired
    private CategoryService service;

    @Autowired
    private CategoryMapper mapper;

    /**
     * gets all categories and returns a list of them as DTOs
     */
    @GetMapping(value = "/")
    public ResponseEntity<List<CategoryDTO>> allCategories() {
        return ResponseEntity.ok().body(mapper.toDTOs(service.getallCategories()));
    }

    /**
     * gets the specified Category by the id from the PathVariable and returns the Category as a DTO
     * @param id
     * @throws IdNotFoundResponseError
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> singleCategory (@PathVariable ("id") UUID id) throws IdNotFoundResponseError {
        return ResponseEntity.ok().body(mapper.toDTO(service.getSingleCategory(id)));
    }

    /**
     * creates a new category and returns it as a Category DTO
     * @param category
     */
    @PostMapping(value = "/")
    @PreAuthorize("hasAuthority('BLOG_CREATE')")
    public ResponseEntity<CategoryDTO> createCategory (@Valid @RequestBody() CategoryDTO category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(service.postACategory(mapper.fromDTO(category))));
    }

    /**
     * updates the Category by searching for it by ID and returns the modified Category as a DTO
     * @param id
     * @param category
     * @throws IdNotFoundResponseError
     */
    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('BLOG_MODIFY')")
    public ResponseEntity<CategoryDTO> updateCategory (@Valid @PathVariable("id") UUID id, @RequestBody CategoryDTO category) throws IdNotFoundResponseError {
        return ResponseEntity.status(200).body(mapper.toDTO(service.putACategory(mapper.fromDTO(category), id)));
    }

    /**
     * deletes a Category
     * @param id
     */
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('BLOG_DELETE')")
    public void deleteACategory(@Valid @PathVariable("id") UUID id) {
        service.deleteACategory(id);
    }
}

