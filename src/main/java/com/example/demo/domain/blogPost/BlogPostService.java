package com.example.demo.domain.blogPost;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class BlogPostService {

    @Autowired
    private BlogPostRepository repository;

    public List<BlogPost> getAllBlogPosts(String filterText) {
        log.info("All blog posts shown");
        List<BlogPost> posts;
        List<BlogPost> filteredPosts = new ArrayList<>();
        posts = repository.findAll();
        if (filterText != null){ //it checks if the reviews name is equal to the filteredName
            for (BlogPost post : posts) {
                if (post.getText().equals(filterText)){
                    filteredPosts.add(post); //when matches it adds to the filteredDrinks
                }
            }
            return filteredPosts;
        }else {
            return posts;
        }
    }

    public BlogPost getSingleBlogPost(UUID id) throws EmptyResultDataAccessException {
        log.info("ID: " + id + " blog post");
        int uuidInInteger = Integer.parseInt(String.valueOf(id));
        return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(uuidInInteger)) ;
    }

    public BlogPost postABlogPost(BlogPost post) {
        log.info("ID: " + post.getId() + " blog post created");
        return repository.save(post);
    }

    public BlogPost putABlogPost(BlogPost post, UUID id) throws EmptyResultDataAccessException {
        log.info("ID: " + id + " blog post updated");
        if (repository.existsById(id)) {
            post.setId(id);
            return repository.save(post);
        }
        int uuidInInteger = Integer.parseInt(String.valueOf(id));
        return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(uuidInInteger));
    }

    public void deleteABlogPost(UUID id) {
        log.info(id + " review deleted");
        repository.deleteById(id);
    }
}
