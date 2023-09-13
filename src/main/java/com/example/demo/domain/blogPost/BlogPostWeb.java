package com.example.demo.domain.blogPost;

import com.example.demo.domain.blogPost.BlogPost;
import com.example.demo.domain.blogPost.BlogPostService;
import com.example.demo.domain.blogPost.dto.BlogPostDTO;
import com.example.demo.domain.blogPost.dto.BlogPostMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
@RequestMapping("/blogs")
@CrossOrigin(origins = "http://localhost:3000/")
public class BlogPostWeb {

    /*
    - Exceptions
     */

    @Autowired
    private BlogPostService service;

    @Autowired
    private BlogPostMapper mapper;

    @GetMapping(value = "/")
    @Operation(summary = "Fetches all Blog Posts", description = "When successful it fetches all posts and returns a JSON-Code with the status code 200.")
    public ResponseEntity<List<BlogPostDTO>> allBlogPosts (@PathVariable (value = "text", required = false) String filterText) {
        return ResponseEntity.ok().body(mapper.toDTOs(service.getAllBlogPosts(filterText)));
    }
    @GetMapping(value = "/{id}")
    @Operation(summary = "Fetches the desired Blog Post", description = "When successful it fetches the wished blog post and returns the JSON-Code with the status code 200.")
    public ResponseEntity<BlogPostDTO> singleBlogPost (@PathVariable ("id") UUID id) throws EmptyResultDataAccessException {
        return ResponseEntity.ok().body(mapper.toDTO(service.getSingleBlogPost(id)));
    }

    @PostMapping(value = "/")
    @PreAuthorize("hasAuthority('BLOG_CREATE')")
    @Operation(summary = "Creates a Blog Post", description = "When successful it creates a blog post with the wished values and returns the JSON-Code of created blog post with the status code 200.")
    public ResponseEntity<BlogPostDTO> createBlogPost (@Valid @RequestBody() BlogPostDTO blogPost)  {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(service.postABlogPost(mapper.fromDTO(blogPost))));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('BLOG_MODIFY')")
    @Operation(summary = "Updates the wished Blog Post", description = "When successful it updates the blog post with the wished values and returns the JSON-Code of the updated blog post with the status code 200.")
    public ResponseEntity<BlogPostDTO> updateBlogPost(@Valid @PathVariable("id") UUID id, @RequestBody BlogPostDTO blogPost) throws EmptyResultDataAccessException {
        return ResponseEntity.status(200).body(mapper.toDTO(service.putABlogPost(mapper.fromDTO(blogPost), id)));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('BLOG_DELETE')")
    @Operation(summary = "Deletes the Blog Post", description = "When successful it deletes the blog post with the status code 200.")
    public void deleteABlogPost(@Valid @PathVariable("id") UUID id) throws EmptyResultDataAccessException {
        service.deleteABlogPost(id);
    }
}
