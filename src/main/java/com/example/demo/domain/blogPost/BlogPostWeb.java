package com.example.demo.domain.blogPost;

import com.example.demo.core.exception.IdNotFoundResponseError;
import com.example.demo.domain.blogPost.BlogPost;
import com.example.demo.domain.blogPost.BlogPostService;
import com.example.demo.domain.blogPost.dto.BlogPostDTO;
import com.example.demo.domain.blogPost.dto.BlogPostMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;



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

    /**
     * fetches all blog posts and returns a list of BlogPostDTO
     */
    @GetMapping(value = "/")
    @Operation(summary = "Fetches all Blog Posts", description = "When successful it fetches all posts and returns a JSON-Code with the status code 200.")
    public ResponseEntity<List<BlogPostDTO>> allBlogPosts () {
        return ResponseEntity.ok().body(mapper.toDTOs(service.getAllBlogPosts()));
    }

    /**
     * fetches blog post by the id and returns one BlogPostDTO
     * @param id
     * @throws EmptyResultDataAccessException
     */
    @GetMapping(value = "/{id}")
    @Operation(summary = "Fetches the desired Blog Post", description = "When successful it fetches the wished blog post and returns the JSON-Code with the status code 200.")
    public ResponseEntity<BlogPostDTO> singleBlogPost (@PathVariable ("id") UUID id) throws IdNotFoundResponseError {
        return ResponseEntity.ok().body(mapper.toDTO(service.getSingleBlogPost(id)));
    }

    /**
     * creates a new Blog Post and returns the created Post as a DTO
     * @param blogPost
     */
    @PostMapping(value = "/")
    @PreAuthorize("hasAuthority('BLOG_CREATE')")
    @Operation(summary = "Creates a Blog Post", description = "When successful it creates a blog post with the wished values and returns the JSON-Code of created blog post with the status code 200.")
    public ResponseEntity<BlogPostDTO> createBlogPost (@Valid @RequestBody() BlogPostDTO blogPost)  {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(service.postABlogPost(mapper.fromDTO(blogPost))));
    }

    /**
     * updates a blog post with the given ID in the PathVariable and returns the updated Post as a DTO
     * @param id
     * @param blogPost
     * @throws EmptyResultDataAccessException
     */
    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('BLOG_MODIFY') || (hasAuthority('BLOG_MODIFY_BY_ID') && @userPermissionEvaluator.isSameUser(authentication.principal.user, #id))")
    @Operation(summary = "Updates the wished Blog Post", description = "When successful it updates the blog post with the wished values and returns the JSON-Code of the updated blog post with the status code 200.")
    public ResponseEntity<BlogPostDTO> updateBlogPost(@Valid @PathVariable("id") UUID id, @RequestBody BlogPostDTO blogPost) throws IdNotFoundResponseError {
        return ResponseEntity.status(200).body(mapper.toDTO(service.putABlogPost(mapper.fromDTO(blogPost), id)));
    }

    /**
     * deletes a Blog Post
     * @param id
     */
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('BLOG_DELETE') || (hasAuthority('BLOG_DELETE_BY_ID') && @userPermissionEvaluator.isSameUser(authentication.principal.user, #id))")
    @Operation(summary = "Deletes the Blog Post", description = "When successful it deletes the blog post with the status code 200.")
    public void deleteABlogPost(@Valid @PathVariable("id") UUID id) {
        service.deleteABlogPost(id);
    }

    @GetMapping("/{id}/categories/{pageNumber}")
    public ResponseEntity<List<BlogPostDTO>> retrieveAllBlogPostsByCategories(@PathVariable("id") UUID id, @PathVariable("pageNumber") int pageNumber){
        Pageable selectedPages = PageRequest.of(pageNumber, 5);
        return ResponseEntity.ok().body(mapper.toDTOs(service.getBlogPostByCategories(id, selectedPages)));
    }
}
