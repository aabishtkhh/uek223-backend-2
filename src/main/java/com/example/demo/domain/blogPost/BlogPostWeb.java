package com.example.demo.domain.blogPost;

import com.example.demo.domain.blogPost.BlogPost;
import com.example.demo.domain.blogPost.BlogPostService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/blogs")
@CrossOrigin(origins = "http://localhost:3000/")
public class BlogPostWeb {

    /*
    - Exceptions
    - Filters
     */

    @Autowired
    private BlogPostService service;

    @GetMapping
    @Operation(summary = "Fetches all Blog Posts", description = "When successful it fetches all posts and returns a JSON-Code with the status code 200.")
    public ResponseEntity<List<BlogPost>> allBlogPosts () {
        return ResponseEntity.ok().body(service.getAllBlogPosts());
    }
    @GetMapping(value = "/{id}")
    @Operation(summary = "Fetches the desired Blog Post", description = "When successful it fetches the wished blog post and returns the JSON-Code with the status code 200.")
    public ResponseEntity<BlogPost> singleBlogPost (@PathVariable ("id") UUID id) {
        return ResponseEntity.ok().body(service.getSingleBlogPost(id));
    }

    @PostMapping(value = "/")
    @PreAuthorize("hasAuthority('BLOG_CREATE')")
    @Operation(summary = "Creates a Blog Post", description = "When successful it creates a blog post with the wished values and returns the JSON-Code of created blog post with the status code 200.")
    public ResponseEntity<BlogPost> createBlogPost (@Valid @RequestBody() BlogPost blogPost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.postABlogPost(blogPost));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('BLOG_MODIFY')")
    @Operation(summary = "Updates the wished Blog Post", description = "When successful it updates the blog post with the wished values and returns the JSON-Code of the updated blog post with the status code 200.")
    public ResponseEntity<BlogPost> updateBlogPost(@Valid @PathVariable("id")UUID id, @RequestBody BlogPost blogPost) {
        return ResponseEntity.status(200).body(service.putABlogPost(blogPost, id));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('BLOG_DELETE')")
    @Operation(summary = "Deletes the Blog Post", description = "When successful it deletes the blog post with the status code 200.")
    public void deleteABlogPost(@Valid @PathVariable("id") UUID id) {
        service.deleteABlogPost(id);
    }


}
