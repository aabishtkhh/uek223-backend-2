package com.example.demo.domain.blogPost.dto;

import com.example.demo.domain.blogPost.dto.BlogPost;
import com.example.demo.domain.blogPost.dto.BlogPostRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class BlogPostService {

    /**
     * Need to add:
     * - Exceptions
     * - Filters in getAllBlogPosts()
     */

    @Autowired
    private BlogPostRepository repository;

    public List<BlogPost> getAllBlogPosts () {
        log.info("All blog posts shown");
        return repository.findAll();
    }

    public BlogPost getSingleBlogPost (UUID id) {
        log.info("ID: " + id + " blog post");
        return repository.findById(id).orElseThrow();
    }

    public BlogPost postABlogPost (BlogPost post) {
        log.info("ID: " + post.getId() + " blog post created");
        return repository.save(post);
    }

    public BlogPost putABlogPost (BlogPost post, UUID id) {
        log.info("ID: " + id + " blog post updated");
        if(repository.existsById(id)) {
            post.setId(id);
            return repository.save(post);
        }
        return repository.findById(id).orElseThrow();
    }

    public void deleteABlogPost (UUID id) {
        log.info(id + " review deleted");
        repository.deleteById(id);
    }
}
